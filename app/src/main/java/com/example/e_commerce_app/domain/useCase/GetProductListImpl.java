package com.example.e_commerce_app.domain.useCase;

import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

public class GetProductListImpl implements GetProductList {
    private final ProductsRepository productsRepository;

    public GetProductListImpl(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    @Override
    public Result<ProductCategory> call() {
        return productsRepository.getProductList();
    }
}
