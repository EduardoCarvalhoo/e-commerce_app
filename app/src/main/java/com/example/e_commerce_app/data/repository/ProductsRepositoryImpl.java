package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsApiDataSource;
import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

public class ProductsRepositoryImpl implements ProductsRepository {

    private final ProductsApiDataSource productsApiDataSource;

    public ProductsRepositoryImpl(ProductsApiDataSource productsApiDataSource) {
        this.productsApiDataSource = productsApiDataSource;
    }

    @Override
    public Result<ProductCategory> getProductList() {
        return productsApiDataSource.getProducts();
    }
}
