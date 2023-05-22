package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.Product;

import javax.inject.Inject;

public class SetFavoriteProductDeletionImpl implements SetFavoriteProductDeletion {
    private final ProductsRepository productsRepository;

    @Inject
    public SetFavoriteProductDeletionImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }


    @Override
    public void call(Product product) {
        productsRepository.setFavoriteProductDeletion(product);
    }
}
