package com.example.e_commerce_app.data.remote.productsCategory.dataSource;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.e_commerce_app.data.remote.rest.ProductsApiService;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductsCategoryApiDataSourceImpl implements ProductsCategoryApiDataSource {
    private final ProductsApiService service;

    @Inject
    public ProductsCategoryApiDataSourceImpl(ProductsApiService service) {
        this.service = service;
    }

    @Override
    public void getProducts(Function<Result<ProductCategoryList>, Void> callback) {
        Call<List<String>> call = service.getProductCategoryList();
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(@NonNull Call<List<String>> call, @NonNull Response<List<String>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<String> productCategoryListResponse = response.body();
                    ProductCategoryList productCategoryList = new ProductCategoryList(productCategoryListResponse);
                    callback.apply(new Result.Success<>(productCategoryList));
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<String>> call, @NonNull Throwable t) {

            }
        });

    }
}
