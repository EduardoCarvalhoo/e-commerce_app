package com.example.e_commerce_app.presentation.home.details;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.databinding.ActivityDetailsBinding;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.presentation.home.HomeActivity;
import com.example.e_commerce_app.presentation.home.ProductViewModel;
import com.example.e_commerce_app.utils.ConstantsConfiguration;
import com.example.e_commerce_app.utils.Exceptions.NetworkErrorException;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    private String productId;
    private ImageButton favoriteButton;
    private ProductViewModel viewModel;
    private Product product;
    private final List<Product> favoriteProductsArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        favoriteButton = binding.detailsFavoriteImageButton;
        productId = (String) getIntent().getSerializableExtra(ConstantsConfiguration.PRODUCT_KEY);
        getProductById();
        setupObserver();
        configureBackButton();
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
        viewModel.getFavoriteProducts();
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

        viewModel.favoriteProductDataSuccessfullyReadLiveData.observe(this, favoriteProducts -> favoriteProductsArrayList.addAll(favoriteProducts.getProducts()));
    }

    public void defineDataPassForDetailItems() {
        Glide.with(this).load(product.getImageUrl()).into(binding.detailsImageView);
        binding.detailsProductName.setText(product.getTitle());
        binding.detailsDevicePriceTextView.setText(getResources().getString(R.string.item_product_dollar_sign_real, product.getPrice()));
        binding.detailsDeviceCategoryTextView.setText(product.getCategory());
        binding.detailsBrandTextView.setText(product.getBrand());
        binding.detailsProductDescriptionTextView.setText(product.getDescription());
        favoriteButton.setVisibility(View.VISIBLE);
        binding.detailsProgressBar.setVisibility(View.GONE);
        setFavoriteButtonStatus();

    }

    public void showAlertDialog(String message, Context context, DialogInterface.OnClickListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(message).setPositiveButton(context.getString(R.string.dialog_positive_button_message_text), listener).setNegativeButton(context.getString(R.string.dialog_negative_button_message_text), null);
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void setFavoriteButtonStatus() {
        if (searchIfProductContainsInTheListOfFavoriteProducts()) {
            favoriteButton.setSelected(true);
        }
    }

    public boolean searchIfProductContainsInTheListOfFavoriteProducts() {
        for (Product products : favoriteProductsArrayList) {
            if (products.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public void setFavoriteButtonClick() {
        favoriteButton.setOnClickListener(v -> {
            favoriteButton.setSelected(!favoriteButton.isSelected());
            if (favoriteButton.isSelected()) {
                saveFavoriteProduct();
            } else {
                setDeletionOfFavoriteProduct();
            }
        });
    }

    public void saveFavoriteProduct() {
        viewModel.setFavoriteProductSave(product);
    }

    public void setDeletionOfFavoriteProduct() {
        viewModel.setFavoriteProductDeletion(product);
    }
}