package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class GetSearchedProductsImpl implements GetSearchedProducts {
    private final ProductsRepository productsRepository;

    @Inject
    public GetSearchedProductsImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void call(Function<Result<ProductList>, Void> callback, String productName) {
        productsRepository.getSearchedProducts(callback, productName);
    }
}
