package com.example.e_commerce_app.presentation.home.favorites;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce_app.databinding.ActivityFavoriteProductsBinding;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.presentation.home.HomeActivity;
import com.example.e_commerce_app.presentation.home.adapter.HomeAdapter;
import com.example.e_commerce_app.presentation.home.details.DetailsActivity;
import com.example.e_commerce_app.utils.ConstantsConfiguration;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class FavoriteProductsActivity extends AppCompatActivity {
    private ActivityFavoriteProductsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureBackButton();
        setReadSharedPreferences();
    }

    public void configureBackButton() {
        binding.favoriteProductsBackImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavoriteProductsActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    public void setReadSharedPreferences() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPrefs", MODE_PRIVATE);
        Gson gson = new Gson();
        String response = sharedPreferences.getString("MyPrefs", "");
        Type type = new TypeToken<ArrayList<Product>>() {
        }.getType();
        List<Product> productArrayList = gson.fromJson(response, type);
        ProductList productList = new ProductList(productArrayList);
        setupRecyclerview(productList);
    }

    public void setupRecyclerview(ProductList productList) {
        Function<Product, Void> onProductClick = product -> {
            setActivityCall(product);
            return null;
        };
        if (productList.getProducts().isEmpty() || productList.getProducts() == null) {
            List<Product> products = new ArrayList<>();
            ProductList productList1 = new ProductList(products);
            HomeAdapter homeAdapter = new HomeAdapter(productList1, onProductClick);
            binding.favoriteProductsRecyclerView.setAdapter(homeAdapter);
        } else {
            HomeAdapter homeAdapter = new HomeAdapter(productList, onProductClick);
            binding.favoriteProductsRecyclerView.setAdapter(homeAdapter);
        }
    }

    public void setActivityCall(Product product) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(ConstantsConfiguration.ProductKey, product.getId());
        startActivity(intent);
    }
}