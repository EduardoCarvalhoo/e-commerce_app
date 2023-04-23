package com.example.e_commerce_app.domain.model;

import java.util.List;

public class ProductCategoryList {

    private List<String> categoryList;

    public ProductCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<String> categoryList) {
        this.categoryList = categoryList;
    }
}
