package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsCategoryRepository;
import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

import javax.inject.Inject;

public class GetProductCategoryImpl implements GetProductCategory {
    private final ProductsCategoryRepository productsRepository;

    @Inject
    public GetProductCategoryImpl(ProductsCategoryRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Result<ProductCategory> call() {
        return productsRepository.getProductList();
    }
}
