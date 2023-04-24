package com.example.e_commerce_app.presentation.home;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commerce_app.R;
import com.example.e_commerce_app.databinding.ActivityHomeBinding;
import com.example.e_commerce_app.domain.model.NetworkErrorException;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.presentation.details.DetailsActivity;
import com.example.e_commerce_app.presentation.home.adapter.HomeAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.function.Function;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        setContentView(binding.getRoot());

        viewModel.getProductCategory();
        setupObserver();
        observeClicksOnLayoutTab();
        setupSearchEditTextListener();
        binding.homeProgressBar.setVisibility(View.VISIBLE);
        setMenu();
    }

    private void setupSearchEditTextListener() {
        binding.homeSearchProductFieldEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(HomeActivity.this, R.string.home_search_not_implemented_message, Toast.LENGTH_SHORT).show();
            }
        });
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

    public void showAlertDialog(String message, Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message)
                .setPositiveButton(context.getString(R.string.dialog_positive_button_message_text), listener)
                .setNegativeButton(context.getString(R.string.dialog_negative_button_message_text), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setMenu() {
        binding.homeMenuImageButton.setOnClickListener(v -> Toast.makeText(HomeActivity.this,
                R.string.home_menu_setting_not_implemented_message, Toast.LENGTH_SHORT).show());
    }

    public void setActivityCall(Product product) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra("product", product);
        startActivity(intent);
    }
}