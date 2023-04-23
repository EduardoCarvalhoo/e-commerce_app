package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface GetProductCategories {
    void call(Function<Result<ProductCategoryList>, Void> callback);
}

