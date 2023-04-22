package com.example.e_commerce_app.data.remote.rest;

import com.example.e_commerce_app.data.remote.productsCategory.model.ProductListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApiService {
    @GET("products/categories")
    Call<ProductListResponse> getProductList();
}
