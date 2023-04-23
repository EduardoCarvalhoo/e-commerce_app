package com.example.e_commerce_app.data.remote.productsCategory.dataSource;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface ProductsCategoryApiDataSource {
    void getProductsCategory(Function<Result<ProductCategoryList>, Void> callback);
}
