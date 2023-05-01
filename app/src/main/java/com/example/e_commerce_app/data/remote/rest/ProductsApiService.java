package com.example.e_commerce_app.data.remote.rest;

import com.example.e_commerce_app.data.model.ProductListResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ProductsApiService {
    @GET("products/categories")
    Call<List<String>> getProductCategoryList();

    @GET("/products/category/{productCategoryName}")
    Call<ProductListResponse> getProducts(@Path("productCategoryName") String productCategoryName);

    @GET("/products/search")
    Call<ProductListResponse> getSearchedProducts(@Query("q") String productName);
}
