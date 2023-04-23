package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class GetProductsImpl implements GetProducts {
    private final ProductsRepository productsRepository;

    @Inject
    public GetProductsImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void call(Function<Result<ProductList>, Void> callback, String productCategoryName) {
        productsRepository.getProducts(callback, productCategoryName);
    }
}
