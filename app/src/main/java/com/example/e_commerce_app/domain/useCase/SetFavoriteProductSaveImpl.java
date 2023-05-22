package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.Product;

import javax.inject.Inject;

public class SetFavoriteProductSaveImpl implements SetFavoriteProductSave {
    private final ProductsRepository productsRepository;

    @Inject
    public SetFavoriteProductSaveImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void call(Product product) {
        productsRepository.saveFavoriteProduct(product);
    }
}
