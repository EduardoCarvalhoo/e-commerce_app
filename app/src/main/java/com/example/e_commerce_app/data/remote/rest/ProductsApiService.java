package com.example.e_commerce_app.data.remote.rest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductsApiService {
    @GET("products/categories")
    Call<List<String>> getProductCategoryList();
}
