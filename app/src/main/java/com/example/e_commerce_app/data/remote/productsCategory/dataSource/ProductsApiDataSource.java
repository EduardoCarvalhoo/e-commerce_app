package com.example.e_commerce_app.data.remote.productsCategory.dataSource;

import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface ProductsApiDataSource {
    void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName);
}
