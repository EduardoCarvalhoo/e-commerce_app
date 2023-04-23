package com.example.e_commerce_app.presentation.home;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.e_commerce_app.databinding.ActivityHomeBinding;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.presentation.home.adapter.HomeAdapter;
import com.google.android.material.tabs.TabLayout;

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

    }

    public void setupObserver() {
        viewModel.productCategoryDataSuccessfullyReadLiveData.observe(this,
                productCategoryList -> {
                    for (String productCategory : productCategoryList) {
                        binding.homeTabLayout.addTab(binding.homeTabLayout.newTab().setText(productCategory));
                    }
                }
        );

        viewModel.productListDataGetSuccessfullyLiveData.observe(this, productList -> {
            setupRecyclerview(productList);
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
        HomeAdapter homeAdapter = new HomeAdapter(productList);
        binding.homeRecyclerView.setAdapter(homeAdapter);
    }
}