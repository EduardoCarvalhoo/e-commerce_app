package com.example.e_commerce_app.presentation.favorites;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce_app.databinding.ActivityFavoriteProductsBinding;
import com.example.e_commerce_app.presentation.home.HomeActivity;

public class FavoriteProductsActivity extends AppCompatActivity {
    private ActivityFavoriteProductsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        configureBackButton();
    }

    public void configureBackButton() {
        binding.favoriteProductsBackImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavoriteProductsActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}