package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface GetSingleProduct {
    void call(Function<Result<Product>, Void> callback, String productId);
}
