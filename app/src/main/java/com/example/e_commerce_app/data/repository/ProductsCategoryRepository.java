package com.example.e_commerce_app.data.repository;

import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

public interface ProductsCategoryRepository {
    Result<ProductCategory> getProductList();
}
