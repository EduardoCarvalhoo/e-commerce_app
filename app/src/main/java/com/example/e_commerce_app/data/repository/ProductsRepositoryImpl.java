package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.data.remote.products.dataSource.ProductsApiDataSource;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class ProductsRepositoryImpl implements ProductsRepository {
    private final ProductsApiDataSource productsApiDataSource;

    @Inject
    public ProductsRepositoryImpl(ProductsApiDataSource productsApiDataSource) {
        this.productsApiDataSource = productsApiDataSource;
    }

    @Override
    public void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName) {
        productsApiDataSource.getProducts(callback, productCategoryName);
    }

    @Override
    public void getProductCategories(Function<Result<ProductCategoryList>, Void> callback) {
        productsApiDataSource.getProductCategories(callback);
    }
}
