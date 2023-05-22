package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.domain.model.Product;

public interface SetFavoriteProductDeletion {
    void call(Product product);
}
