package com.example.e_commerce_app.data.remote.model;

public class ProductCategoriesResponse {
    private String slug;
    private String name;
    private String url;

    public ProductCategoriesResponse(String slug, String name, String url) {
        this.slug = slug;
        this.name = name;
        this.url = url;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
