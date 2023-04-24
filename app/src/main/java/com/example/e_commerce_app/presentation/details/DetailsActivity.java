package com.example.e_commerce_app.presentation.details;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.databinding.ActivityDetailsBinding;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.presentation.home.HomeActivity;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    public Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        defineDataPassForDetailItems();
        configureBackButton();
        configureFavoriteButton();
        configureAddButton();
    }

    public void configureBackButton() {
        binding.detailsBackImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    public void configureFavoriteButton() {
        binding.detailsFavoriteImageButton.setOnClickListener(v -> Toast.makeText(DetailsActivity.this, R.string.details_favorite_not_implemented_message, Toast.LENGTH_SHORT).show());
    }

    public void configureAddButton() {
        binding.detailsAddToCartButton.setOnClickListener(v -> Toast.makeText(DetailsActivity.this, R.string.details_add_button_not_implemented_message, Toast.LENGTH_SHORT).show());
    }

    public void defineDataPassForDetailItems() {
        product = (Product) getIntent().getSerializableExtra("product");
        Glide.with(this).load(product.getImageUrl()).into(binding.detailsImageView);
        binding.detailsProductName.setText(product.getTitle());
        binding.detailsDevicePriceTextView.setText(getResources().getString(R.string.item_product_dollar_sign_real, product.getPrice()));
        binding.detailsDeviceCategoryTextView.setText(product.getCategory());
        binding.detailsBrandTextView.setText(product.getBrand());
        binding.detailsProductDescriptionTextView.setText(product.getDescription());
    }

}