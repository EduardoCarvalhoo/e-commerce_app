package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.domain.model.Product;

public interface SetFavoriteProductSave {
    void call(Product product);
}
