package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface ProductsRepository {
    void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName);
}
