package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsCategoryApiDataSource;
import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

import javax.inject.Inject;

public class ProductsCategoryRepositoryImpl implements ProductsCategoryRepository {

    private final ProductsCategoryApiDataSource productsApiDataSource;

    @Inject
    public ProductsCategoryRepositoryImpl(ProductsCategoryApiDataSource productsApiDataSource) {
        this.productsApiDataSource = productsApiDataSource;
    }

    @Override
    public Result<ProductCategory> getProductList() {
        return productsApiDataSource.getProducts();
    }
}
