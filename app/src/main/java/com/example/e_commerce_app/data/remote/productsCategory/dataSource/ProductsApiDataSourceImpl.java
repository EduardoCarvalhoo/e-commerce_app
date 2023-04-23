package com.example.e_commerce_app.data.remote.productsCategory.dataSource;

import androidx.annotation.NonNull;

import com.example.e_commerce_app.data.model.ProductListResponse;
import com.example.e_commerce_app.data.remote.rest.ProductsApiService;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsApiDataSourceImpl implements ProductsApiDataSource {
    private final ProductsApiService service;

    @Inject
    public ProductsApiDataSourceImpl(ProductsApiService service) {
        this.service = service;
    }

    @Override
    public void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName) {
        Call<ProductListResponse> call = service.getProducts(productCategoryName);
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductListResponse> call, @NonNull Response<ProductListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductListResponse productListResponse = response.body();
                    List<Product> mappedProductList = productListResponse.getProducts().stream()
                            .map(productResponse -> new Product(
                                    productResponse.getId(), productResponse.getTitle(),
                                    productResponse.getDescription(), productResponse.getPrice(),
                                    productResponse.getBrand(), productResponse.getCategory(),
                                    productResponse.getImages().stream().findFirst().orElse("")
                            ))
                            .collect(Collectors.toList());
                    ProductList productList = new ProductList(mappedProductList);
                    callback.apply(new Result.Success<>(productList));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductListResponse> call, @NonNull Throwable t) {

            }
        });
    }
}
