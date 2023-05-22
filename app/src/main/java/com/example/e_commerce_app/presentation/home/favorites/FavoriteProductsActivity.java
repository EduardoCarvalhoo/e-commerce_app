package com.example.e_commerce_app.presentation.home.favorites;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commerce_app.databinding.ActivityFavoriteProductsBinding;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.presentation.home.HomeActivity;
import com.example.e_commerce_app.presentation.home.ProductViewModel;
import com.example.e_commerce_app.presentation.home.adapter.HomeAdapter;
import com.example.e_commerce_app.presentation.home.details.DetailsActivity;
import com.example.e_commerce_app.utils.ConstantsConfiguration;

import java.util.function.Function;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class FavoriteProductsActivity extends AppCompatActivity {
    private ActivityFavoriteProductsBinding binding;
    private ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoriteProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        configureBackButton();
        getFavoriteProducts();
        setupObserver();
    }

    public void configureBackButton() {
        binding.favoriteProductsBackImageButton.setOnClickListener(v -> {
            Intent intent = new Intent(FavoriteProductsActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }

    public void getFavoriteProducts() {
        viewModel.getFavoriteProducts();
    }

    public void setupObserver() {
        viewModel.favoriteProductDataSuccessfullyReadLiveData.observe(this, this::setupRecyclerview);
    }

    public void setupRecyclerview(ProductList productList) {
        Function<Product, Void> onProductClick = product -> {
            setActivityCall(product);
            return null;
        };
        if (!productList.getProducts().isEmpty() && productList.getProducts() != null) {
            HomeAdapter homeAdapter = new HomeAdapter(productList, onProductClick);
            binding.favoriteProductsRecyclerView.setAdapter(homeAdapter);
            binding.favoriteProductsNoProductsAddedTextView.setVisibility(View.GONE);
        } else {
            binding.favoriteProductsRecyclerView.setVisibility(View.GONE);
            binding.favoriteProductsNoProductsAddedTextView.setVisibility(View.VISIBLE);
        }
    }

    public void setActivityCall(Product product) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(ConstantsConfiguration.PRODUCT_KEY, product.getId());
        startActivity(intent);
    }
}