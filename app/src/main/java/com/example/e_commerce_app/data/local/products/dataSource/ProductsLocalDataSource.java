package com.example.e_commerce_app.data.local.products.dataSource;

import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

public interface ProductsLocalDataSource {
    void SaveProduct(Product product);

    void GetProductFavorite(Function<Result<ProductList>, Void> callback);

    void SetFavoriteProductDeletion(Product product);
}
