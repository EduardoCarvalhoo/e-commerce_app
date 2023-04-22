package com.example.e_commerce_app.data.remote.rest;

import static com.example.e_commerce_app.utils.ConstantsConfiguration.BASE_URL;

import com.example.e_commerce_app.data.remote.productsCategory.model.ProductListResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApiService {
    @GET(BASE_URL)
    Call<ProductListResponse> getProductList();
}
