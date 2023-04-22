package com.example.e_commerce_app.domain.model;

import java.util.List;

public class ProductCategory {

    private List<String> category;

    public ProductCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }
}
