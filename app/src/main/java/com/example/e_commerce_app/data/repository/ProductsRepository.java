package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface ProductsRepository {
    void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName);

    void getProductCategories(Function<Result<ProductCategoryList>, Void> callback);

    void getSearchedProducts(Function<Result<ProductList>, Void> callback, String productName);

    void getSingleProduct(Function<Result<Product>, Void> callback, String productId);
}
