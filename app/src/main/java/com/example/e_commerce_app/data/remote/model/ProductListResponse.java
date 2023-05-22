package com.example.e_commerce_app.data.remote.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductListResponse {
    @SerializedName("products")
    private List<ProductResponse> products;

    public ProductListResponse(List<ProductResponse> products) {
        this.products = products;
    }

    public List<ProductResponse> getProducts() {
        return products;
    }

    public void setProducts(List<ProductResponse> products) {
        this.products = products;
    }
}
