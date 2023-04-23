package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsCategoryRepository;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.function.Function;

import javax.inject.Inject;

public class GetProductCategoriesImpl implements GetProductCategories {
    private final ProductsCategoryRepository productsCategoryRepository;

    @Inject
    public GetProductCategoriesImpl(ProductsCategoryRepository productsRepository) {
        this.productsCategoryRepository = productsRepository;
    }

    @Override
    public void call(Function<Result<ProductCategoryList>, Void> callback) {
        productsCategoryRepository.getProductList(callback);
    }
}
