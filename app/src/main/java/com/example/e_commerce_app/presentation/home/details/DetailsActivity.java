package com.example.e_commerce_app.presentation.home.details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.databinding.ActivityDetailsBinding;
import com.example.e_commerce_app.domain.model.NetworkErrorException;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.presentation.home.HomeActivity;
import com.example.e_commerce_app.presentation.home.HomeViewModel;
import com.example.e_commerce_app.utils.ConstantsConfiguration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private String productId;
    private ImageButton favoriteButton;
    private final ArrayList<Product> productArrayList = new ArrayList<>();
    private HomeViewModel viewModel;
    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        favoriteButton = binding.detailsFavoriteImageButton;
        productId = (String) getIntent().getSerializableExtra(ConstantsConfiguration.PRODUCT_KEY);
        getProductById();
        setupObserver();
        configureBackButton();
        setReadSharedPreferences();
        setFavoriteButtonClick();
        configureAddButton();
    }

    public void configureBackButton() {
        binding.detailsBackImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        });
    }

    public void configureAddButton() {
        binding.detailsAddToCartButton.setOnClickListener(v -> Toast.makeText(DetailsActivity.this, R.string.details_add_button_not_implemented_message, Toast.LENGTH_SHORT).show());
    }

    public void getProductById() {
        viewModel.getSingleProduct(productId);
        binding.detailsProgressBar.setVisibility(View.VISIBLE);
    }

    public void setupObserver() {
        viewModel.productDataSuccessfullyReadLiveData.observe(this, productData -> {
            if (productData != null) {
                product = productData;
                defineDataPassForDetailItems();
            }
        });
        viewModel.errorReadingProductDataLiveData.observe(this, throwable -> {
            if (throwable instanceof NetworkErrorException) {
                showAlertDialog(getString(R.string.no_internet_connection_error_text), this, (dialog, which) -> viewModel.getSingleProduct(productId));
            } else {
                showAlertDialog(getString(R.string.generic_error_text), this, (dialog, which) -> viewModel.getSingleProduct(productId));
            }
        });
    }

    public void defineDataPassForDetailItems() {
        binding.detailsProgressBar.setVisibility(View.GONE);
        Glide.with(this).load(product.getImageUrl()).into(binding.detailsImageView);
        binding.detailsProductName.setText(product.getTitle());
        binding.detailsDevicePriceTextView.setText(getResources().getString(R.string.item_product_dollar_sign_real, product.getPrice()));
        binding.detailsDeviceCategoryTextView.setText(product.getCategory());
        binding.detailsBrandTextView.setText(product.getBrand());
        binding.detailsProductDescriptionTextView.setText(product.getDescription());
        setFavoriteButtonStatus();
    }

    public void showAlertDialog(String message, Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setPositiveButton(context.getString(R.string.dialog_positive_button_message_text), listener).setNegativeButton(context.getString(R.string.dialog_negative_button_message_text), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setReadSharedPreferences() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String productListString = sharedPreferences.getString("MyPrefs", "");

        if (!productListString.equals("[null]")) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Product>>() {
            }.getType();
            List<Product> productList = gson.fromJson(productListString, type);
            productArrayList.addAll(productList);
        }
    }

    public boolean searchIfProductContainsInTheListOfProducts() {
        for (Product products : productArrayList) {
            if (products.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public void setFavoriteButtonStatus() {
        favoriteButton.setVisibility(View.VISIBLE);
        if (productArrayList != null && product != null) {
            if (searchIfProductContainsInTheListOfProducts()) {
                favoriteButton.setSelected(true);
            }
        }
    }

    public void setFavoriteButtonClick() {
        favoriteButton.setOnClickListener(v -> {
            favoriteButton.setSelected(!favoriteButton.isSelected());
            setFavoritesListSave();
        });
    }

    public void setFavoritesListSave() {
        if (favoriteButton.isSelected() && !searchIfProductContainsInTheListOfProducts()) {
            productArrayList.add(product);
            saveFavoriteProductsInSharedPreferences();
        } else if (!favoriteButton.isSelected() && searchIfProductContainsInTheListOfProducts()) {
            productArrayList.remove(product);
            saveFavoriteProductsInSharedPreferences();
        }
    }

    public void saveFavoriteProductsInSharedPreferences() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(productArrayList);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("MyPrefs", json);
        editor.apply();
    }
}