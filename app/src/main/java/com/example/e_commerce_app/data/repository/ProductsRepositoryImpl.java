package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.data.local.products.dataSource.ProductsLocalDataSource;
import com.example.e_commerce_app.data.remote.products.dataSource.ProductsApiDataSource;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class ProductsRepositoryImpl implements ProductsRepository {
    private final ProductsApiDataSource productsApiDataSource;
    private final ProductsLocalDataSource productsLocalDataSource;

    @Inject
    public ProductsRepositoryImpl(ProductsApiDataSource productsApiDataSource, ProductsLocalDataSource productsLocalDataSource) {
        this.productsApiDataSource = productsApiDataSource;
        this.productsLocalDataSource = productsLocalDataSource;
    }

    @Override
    public void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName) {
        productsApiDataSource.getProducts(callback, productCategoryName);
    }

    @Override
    public void getProductCategories(Function<Result<ProductCategoryList>, Void> callback) {
        productsApiDataSource.getProductCategories(callback);
    }

    @Override
    public void getSearchedProducts(Function<Result<ProductList>, Void> callback, String productName) {
        productsApiDataSource.getSearchedProducts(callback, productName);
    }

    @Override
    public void getSingleProduct(Function<Result<Product>, Void> callback, String productId) {
        productsApiDataSource.getSingleProduct(callback, productId);
    }

    @Override
    public void saveFavoriteProduct(Product product) {
        productsLocalDataSource.SaveProduct(product);
    }

    @Override
    public void getProductFavorite(Function<Result<ProductList>, Void> callback) {
        productsLocalDataSource.GetProductFavorite(callback);
    }

    @Override
    public void setFavoriteProductDeletion(Product product) {
        productsLocalDataSource.SetFavoriteProductDeletion(product);
    }
}
