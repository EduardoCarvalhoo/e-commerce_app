package com.example.e_commerce_app.presentation.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commerce_app.databinding.ActivityHomeBinding;

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

    }

    public void setupObserver() {
        viewModel.productCategoryDataSuccessfullyReadLiveData.observe(this,
                productCategoryList -> {
                    for (String productCategory : productCategoryList) {
                        binding.homeTabLayout.addTab(binding.homeTabLayout.newTab().setText(productCategory));
                        setSearchForProducts(productCategory);
                    }
                }
        );
    }

    public void setSearchForProducts(String productCategory) {
        viewModel.getProducts(productCategory);
    }

}