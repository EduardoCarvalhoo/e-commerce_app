package com.example.e_commerce_app.presentation.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;
import com.example.e_commerce_app.domain.useCase.GetProductCategories;
import com.example.e_commerce_app.domain.useCase.GetProducts;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GetProductCategories getProductCategories;
    private final GetProducts getProducts;

    private final MutableLiveData<List<String>> _productCategoryDataSuccessfullyReadMutableLiveData = new MutableLiveData<>();
    LiveData<List<String>> productCategoryDataSuccessfullyReadLiveData = _productCategoryDataSuccessfullyReadMutableLiveData;
    private final MutableLiveData<Exception> _errorReadingProductCategoryDataMutableLiveData = new MutableLiveData<>();
    LiveData<Exception> errorReadingProductCategoryDataLiveData = _errorReadingProductCategoryDataMutableLiveData;
    private final MutableLiveData<ProductList> _productListDataGetSuccessfullyMutableLiveData = new MutableLiveData<>();
    LiveData<ProductList> productListDataGetSuccessfullyLiveData = _productListDataGetSuccessfullyMutableLiveData;
    private final MutableLiveData<Exception> _errorReadingProductListDataLiveDataMutableLiveData = new MutableLiveData<>();
    LiveData<Exception> errorReadingProductListDataLiveData = _errorReadingProductListDataLiveDataMutableLiveData;

    @Inject
    public HomeViewModel(GetProductCategories getProductCategories, GetProducts getProducts) {
        this.getProductCategories = getProductCategories;
        this.getProducts = getProducts;
    }

    public void getProductCategory() {
        Function<Result<ProductCategoryList>, Void> callback = productCategoryListResult -> {
            if (productCategoryListResult instanceof Result.Success) {
                _productCategoryDataSuccessfullyReadMutableLiveData.postValue(((Result.Success<ProductCategoryList>) productCategoryListResult).getData().getCategoryList());
            } else if (productCategoryListResult instanceof Result.Error) {
                _errorReadingProductCategoryDataMutableLiveData.postValue(((Result.Error<ProductCategoryList>) productCategoryListResult).getValue());
            }
            return null;
        };
        getProductCategories.call(callback);
    }

    public void getProducts(String productCategoryName) {
        Function<Result<ProductList>, Void> callback = productListResult -> {
            if (productListResult instanceof Result.Success) {
                _productListDataGetSuccessfullyMutableLiveData.postValue(((Result.Success<ProductList>) productListResult).getData());
            } else if (productListResult instanceof Result.Error) {
                _errorReadingProductListDataLiveDataMutableLiveData.postValue(((Result.Error<ProductList>) productListResult).getValue());
            }
            return null;
        };

        getProducts.call(callback, productCategoryName);
    }
}
