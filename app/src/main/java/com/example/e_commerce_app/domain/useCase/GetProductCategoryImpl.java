package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsCategoryRepository;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class GetProductCategoryImpl implements GetProductCategory {
    private final ProductsCategoryRepository productsRepository;

    @Inject
    public GetProductCategoryImpl(ProductsCategoryRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public void call(Function<Result<ProductCategoryList>, Void> callback) {
        productsRepository.getProductList(callback);
    }
}
