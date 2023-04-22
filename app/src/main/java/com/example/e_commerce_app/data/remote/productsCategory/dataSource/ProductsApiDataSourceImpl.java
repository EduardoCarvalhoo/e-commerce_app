package com.example.e_commerce_app.data.remote.productsCategory.dataSource;

import com.example.e_commerce_app.data.remote.productsCategory.model.ProductListResponse;
import com.example.e_commerce_app.data.remote.rest.ProductsApiService;
import com.example.e_commerce_app.domain.model.ProductCategory;
import com.example.e_commerce_app.domain.result.Result;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsApiDataSourceImpl implements ProductsApiDataSource {
    private final ProductsApiService service;

    public ProductsApiDataSourceImpl(ProductsApiService service) {
        this.service = service;
    }

    @Override
    public Result<ProductCategory> getProducts() {
        Call<ProductListResponse> call = service.getProductList();
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(Call<ProductListResponse> call, Response<ProductListResponse> response) {
                if (response.isSuccessful()) {
                    ProductListResponse productList = response.body();
                }
            }

            @Override
            public void onFailure(Call<ProductListResponse> call, Throwable t) {

            }
        });
        return null;
    }
}
