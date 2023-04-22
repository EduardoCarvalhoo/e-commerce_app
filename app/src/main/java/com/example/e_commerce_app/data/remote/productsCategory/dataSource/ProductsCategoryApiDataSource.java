package com.example.e_commerce_app.data.remote.productsCategory.dataSource;

import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

public interface ProductsCategoryApiDataSource {
    Result<ProductCategory> getProducts();
}
