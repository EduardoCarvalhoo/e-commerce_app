package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

public interface GetProductList {
    Result<ProductCategory> call();
}
