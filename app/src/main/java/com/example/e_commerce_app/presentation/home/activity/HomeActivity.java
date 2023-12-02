package com.example.e_commerce_app.presentation.home.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commerce_app.R;
import com.example.e_commerce_app.databinding.ActivityHomeBinding;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.presentation.home.adapter.HomeAdapter;
import com.example.e_commerce_app.presentation.home.details.DetailsActivity;
import com.example.e_commerce_app.presentation.home.favorites.FavoriteProductsActivity;
import com.example.e_commerce_app.presentation.home.viewModel.ProductViewModel;
import com.example.e_commerce_app.utils.ConstantsConfiguration;
import com.example.e_commerce_app.utils.Exceptions.NetworkErrorException;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.function.Function;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        setContentView(binding.getRoot());

        viewModel.getProductCategory();
        setupObserver();
        observeClicksOnLayoutTab();
        setupSearchEditTextListener();
        binding.homeProgressBar.setVisibility(View.VISIBLE);
        setMenu();
    }

    public void setupObserver() {
        viewModel.productCategoryDataSuccessfullyReadLiveData.observe(this, productCategoryList -> {
            for (String productCategory : productCategoryList) {
                binding.homeTabLayout.addTab(binding.homeTabLayout.newTab().setText(productCategory));
            }
        });


        viewModel.errorReadingProductCategoryDataLiveData.observe(this, throwable -> {
            if (throwable instanceof NetworkErrorException) {
                showAlertDialog(getString(R.string.no_internet_connection_error_text), this, (dialog, which) -> viewModel.getProductCategory());
            } else {
                showAlertDialog(getString(R.string.generic_error_text), this, (dialog, which) -> viewModel.getProductCategory());
            }
        });

        viewModel.productListDataGetSuccessfullyLiveData.observe(this, this::setupRecyclerview);

        viewModel.errorReadingProductListDataLiveData.observe(this, throwable -> {
            DialogInterface.OnClickListener getProductsListener = (dialog, which) -> {
                TabLayout.Tab selectedTab = binding.homeTabLayout.getTabAt(binding.homeTabLayout.getSelectedTabPosition());
                if (selectedTab != null && selectedTab.getText() != null) {
                    String selectedProductCategory = selectedTab.getText().toString();
                    viewModel.getProducts(selectedProductCategory);
                }
            };
            if (throwable instanceof NetworkErrorException) {
                showAlertDialog(getString(R.string.no_internet_connection_error_text), this, getProductsListener);
            } else {
                showAlertDialog(getString(R.string.generic_error_text), this, getProductsListener);
            }
        });

        viewModel.researchedProductsSuccessfullyReadLiveData.observe(this, this::setVisibilityForFilteredProducts);
    }

    public void observeClicksOnLayoutTab() {
        binding.homeTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                CharSequence selectedCategory = tab.getText();
                if (selectedCategory != null) {
                    setSearchForProducts(selectedCategory.toString());
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    public void setSearchForProducts(String productCategoryName) {
        viewModel.getProducts(productCategoryName);
    }

    public void setupRecyclerview(ProductList productList) {
        Function<Product, Void> onProductClick = product -> {
            setActivityCall(product);
            return null;
        };
        binding.homeProgressBar.setVisibility(View.GONE);
        HomeAdapter homeAdapter = new HomeAdapter(productList, onProductClick);
        binding.homeRecyclerView.setAdapter(homeAdapter);
    }


    private void setupSearchEditTextListener() {
        binding.homeSearchProductFieldEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                viewModel.getSearchedProducts(s);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    public void setVisibilityForFilteredProducts(ProductList researchedProducts) {
        String typedText = String.valueOf(binding.homeSearchProductFieldEditText.getText());
        if (typedText.equals("")) {
            setViewReset();
            ProductList productListData = viewModel.productListDataGetSuccessfullyLiveData.getValue();
            setupRecyclerview(productListData);
        } else {
            binding.homeInformativeTitleTextView.setVisibility(View.GONE);
            binding.homeTabLayout.setVisibility(View.GONE);
            setupRecyclerview(researchedProducts);
        }
    }

    public void setViewReset() {
        binding.homeInformativeTitleTextView.setVisibility(View.VISIBLE);
        binding.homeTabLayout.setVisibility(View.VISIBLE);
    }

    public void showAlertDialog(String message, Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setPositiveButton(context.getString(R.string.dialog_positive_button_message_text), listener).setNegativeButton(context.getString(R.string.dialog_negative_button_message_text), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setMenu() {
        DrawerLayout drawerLayout = binding.drawerLayout;
        NavigationView navigationView = binding.homeNavView;
        binding.homeMenuImageButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        navigationView.setNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_menu_favorite) {
                Intent intent = new Intent(HomeActivity.this, FavoriteProductsActivity.class);
                startActivity(intent);
            }
            return false;
        });
    }

    public void setActivityCall(Product product) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(ConstantsConfiguration.PRODUCT_KEY, product.getId());
        startActivity(intent);
    }
}