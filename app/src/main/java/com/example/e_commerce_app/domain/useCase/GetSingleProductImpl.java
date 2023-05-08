package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class GetSingleProductImpl implements GetSingleProduct {
    public final ProductsRepository productsRepository;

    @Inject
    public GetSingleProductImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void call(Function<Result<Product>, Void> callback, String productId) {
        productsRepository.getSingleProduct(callback, productId);
    }
}
