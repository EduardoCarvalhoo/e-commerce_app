package com.example.e_commerce_app.domain.model;

import com.example.e_commerce_app.data.remote.model.ProductCategoriesResponse;

import java.util.List;

public class ProductCategoryList {

    private List<ProductCategoriesResponse> categoryList;

    public ProductCategoryList(List<ProductCategoriesResponse> categoryList) {
        this.categoryList = categoryList;
    }

    public List<ProductCategoriesResponse> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<ProductCategoriesResponse> categoryList) {
        this.categoryList = categoryList;
    }
}
