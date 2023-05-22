package com.example.e_commerce_app.data.local.products.dataSource;

import com.example.e_commerce_app.data.local.products.db.ProductDao;
import com.example.e_commerce_app.data.local.products.model.ProductEntity;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

public class ProductsLocalDataSourceImpl implements ProductsLocalDataSource {
    private final ProductDao productDao;

    @Inject
    public ProductsLocalDataSourceImpl(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void SaveProduct(Product product) {
        ProductEntity productEntity = new ProductEntity(product.getId(), product.getTitle(),
                product.getDescription(), product.getPrice(), product.getBrand(), product.getCategory(),
                product.getImageUrl());
        productDao.insert(productEntity);
    }

    @Override
    public void GetProductFavorite(Function<Result<ProductList>, Void> callback) {
        List<ProductEntity> productListEntity = productDao.getAllProducts();

        List<Product> mappedProductList = productListEntity.stream().map(
                product -> new Product(product.getId(), product.getTitle(), product.getDescription(),
                        product.getPrice(), product.getBrand(), product.getCategory(),
                        product.getImageUrl())).collect(Collectors.toList());
        ProductList productList = new ProductList(mappedProductList);
        callback.apply(new Result.Success<>(productList));
    }

    @Override
    public void SetFavoriteProductDeletion(Product product) {
        ProductEntity productEntity = new ProductEntity(product.getId(), product.getTitle(),
                product.getDescription(), product.getPrice(), product.getBrand(), product.getCategory(),
                product.getImageUrl());
        productDao.delete(productEntity);
    }
}
