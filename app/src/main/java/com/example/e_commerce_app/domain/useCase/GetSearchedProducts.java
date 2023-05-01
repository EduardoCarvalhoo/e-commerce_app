package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface GetSearchedProducts {
    void call(Function<Result<ProductList>, Void> callback, String productName);
}
