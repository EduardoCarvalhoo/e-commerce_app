package com.example.e_commerce_app.presentation.home.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.e_commerce_app.R;
import com.example.e_commerce_app.databinding.ActivityDetailsBinding;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.presentation.home.HomeActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DetailsActivity extends AppCompatActivity {
    private ActivityDetailsBinding binding;
    public Product product;
    private ImageButton favoriteButton;
    private final ArrayList<Product> productArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        defineDataPassForDetailItems();
        configureBackButton();
        setReadSharedPreferences();
        searchIfProductContainsInTheListOfProducts();
        configureFavoriteButton();
        configureAddButton();
    }

    public void configureBackButton() {
        binding.detailsBackImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(DetailsActivity.this, HomeActivity.class);
            startActivity(intent);
        });
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

    public void setReadSharedPreferences() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();

        String response = sharedPreferences.getString("MyPrefs", "");
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        List<Product> productList = gson.fromJson(response, type);
        productArrayList.addAll(productList);
    }

    public boolean searchIfProductContainsInTheListOfProducts() {
        for (Product products : productArrayList) {
            if (products.equals(product)) {
                return true;
            }
        }
        return false;
    }

    public void configureFavoriteButton() {
        favoriteButton = binding.detailsFavoriteImageButton;
        if (searchIfProductContainsInTheListOfProducts()) {
            favoriteButton.setSelected(true);
        }
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