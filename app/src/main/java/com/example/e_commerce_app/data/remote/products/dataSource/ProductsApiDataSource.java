package com.example.e_commerce_app.data.remote.products.dataSource;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface ProductsApiDataSource {
    void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName);
    void getProductCategories(Function<Result<ProductCategoryList>, Void> callback);
}
