package com.example.e_commerce_app.data.remote.productsCategory.model;

import java.util.List;

public class ProductListResponse {
    private List<String> results;

    public ProductListResponse(List<String> results) {
        this.results = results;
    }

    public List<String> getResults() {
        return results;
    }

    public void setResults(List<String> results) {
        this.results = results;
    }
}
