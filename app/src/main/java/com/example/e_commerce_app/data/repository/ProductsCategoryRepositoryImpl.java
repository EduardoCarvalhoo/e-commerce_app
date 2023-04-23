package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsCategoryApiDataSource;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class ProductsCategoryRepositoryImpl implements ProductsCategoryRepository {

    private final ProductsCategoryApiDataSource productsApiDataSource;

    @Inject
    public ProductsCategoryRepositoryImpl(ProductsCategoryApiDataSource productsApiDataSource) {
        this.productsApiDataSource = productsApiDataSource;
    }

    @Override
    public void getProductList(Function<Result<ProductCategoryList>, Void> callback) {
        productsApiDataSource.getProductsCategory(callback);
    }
}
