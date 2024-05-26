package com.example.e_commerce_app.data.remote.products.dataSource;

import androidx.annotation.NonNull;

import com.example.e_commerce_app.data.remote.model.ProductCategoriesResponse;
import com.example.e_commerce_app.data.remote.model.ProductListResponse;
import com.example.e_commerce_app.data.remote.model.ProductResponse;
import com.example.e_commerce_app.data.remote.rest.ProductsApiService;
import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;
import com.example.e_commerce_app.utils.Exceptions.NetworkErrorException;
import com.example.e_commerce_app.utils.Exceptions.ServerErrorException;

import java.io.IOException;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsApiDataSourceImpl implements ProductsApiDataSource {
    private final ProductsApiService service;

    @Inject
    public ProductsApiDataSourceImpl(ProductsApiService service) {
        this.service = service;
    }

    @Override
    public void getProducts(Function<Result<ProductList>, Void> callback, String productCategoryName) {
        Call<ProductListResponse> call = service.getProducts(productCategoryName);
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductListResponse> call, @NonNull Response<ProductListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductListResponse productListResponse = response.body();
                    List<Product> mappedProductList = productListResponse.getProducts().stream()
                            .map(productResponse -> new Product(productResponse.getId(), productResponse
                                    .getTitle(), productResponse.getDescription(), productResponse
                                    .getPrice(), productResponse.getBrand(), productResponse.getCategory(),
                                    productResponse.getImages().stream().findFirst().orElse("")))
                            .collect(Collectors.toList());
                    ProductList productList = new ProductList(mappedProductList);
                    callback.apply(new Result.Success<>(productList));
                } else {
                    callback.apply(new Result.Error<>(new ServerErrorException()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductListResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    callback.apply(new Result.Error<>(new NetworkErrorException()));
                } else {
                    callback.apply(new Result.Error<>(new ServerErrorException()));
                }
            }
        });
    }

    @Override
    public void getProductCategories(Function<Result<ProductCategoryList>, Void> callback) {
        Call<List<ProductCategoriesResponse>> call = service.getProductCategoryList();
        call.enqueue(new Callback<List<ProductCategoriesResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<ProductCategoriesResponse>> call, @NonNull Response<List<ProductCategoriesResponse>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<ProductCategoriesResponse> productCategoryListResponse = response.body();
                    ProductCategoryList productCategoryList = new ProductCategoryList(productCategoryListResponse);
                    callback.apply(new Result.Success<>(productCategoryList));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<ProductCategoriesResponse>> call, @NonNull Throwable t) {
                callback.apply(new Result.Error<>(t));
            }
        });
    }

    @Override
    public void getSearchedProducts(Function<Result<ProductList>, Void> callback, String productName) {
        Call<ProductListResponse> call = service.getSearchedProducts(productName);
        call.enqueue(new Callback<ProductListResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductListResponse> call, @NonNull Response<ProductListResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductListResponse productListResponse = response.body();
                    List<Product> mapSearchedProducts = productListResponse.getProducts().stream().map(productResponse -> new Product(productResponse.getId(), productResponse.getTitle(), productResponse.getDescription(), productResponse.getPrice(), productResponse.getBrand(), productResponse.getCategory(), productResponse.getImages().stream().findFirst().orElse(""))).collect(Collectors.toList());

                    ProductList productList = new ProductList(mapSearchedProducts);
                    callback.apply(new Result.Success<>(productList));
                } else {
                    callback.apply(new Result.Error<>(new ServerErrorException()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductListResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    callback.apply(new Result.Error<>(new NetworkErrorException()));
                } else {
                    callback.apply(new Result.Error<>(new ServerErrorException()));
                }
            }
        });
    }

    @Override
    public void getSingleProduct(Function<Result<Product>, Void> callback, String productId) {
        Call<ProductResponse> call = service.getSingleProduct(productId);
        call.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(@NonNull Call<ProductResponse> call, @NonNull Response<ProductResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ProductResponse productResponse = response.body();
                    Product product = new Product(productResponse.getId(), productResponse.getTitle(), productResponse.getDescription(), productResponse.getPrice(), productResponse.getBrand(), productResponse.getCategory(), productResponse.getImages().stream().findFirst().orElse(""));
                    callback.apply(new Result.Success<>(product));
                } else {
                    callback.apply(new Result.Error<>(new ServerErrorException()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<ProductResponse> call, @NonNull Throwable t) {
                if (t instanceof IOException) {
                    callback.apply(new Result.Error<>(new NetworkErrorException()));
                } else {
                    callback.apply(new Result.Error<>(new ServerErrorException()));
                }
            }
        });
    }
}
