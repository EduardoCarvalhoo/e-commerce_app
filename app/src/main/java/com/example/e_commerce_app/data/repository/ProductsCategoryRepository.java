package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface ProductsCategoryRepository {
    void getProductList(Function<Result<ProductCategoryList>, Void> callback);
}
