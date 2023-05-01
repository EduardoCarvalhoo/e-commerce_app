package com.example.e_commerce_app.presentation.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;
import com.example.e_commerce_app.domain.useCase.GetProductCategories;
import com.example.e_commerce_app.domain.useCase.GetProducts;
import com.example.e_commerce_app.domain.useCase.GetSearchedProducts;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GetProductCategories getProductCategories;
    private final GetProducts getProducts;
    private final GetSearchedProducts getSearchedProducts;

    private final MutableLiveData<List<String>> _productCategoryDataSuccessfullyReadMutableLiveData = new MutableLiveData<>();
    LiveData<List<String>> productCategoryDataSuccessfullyReadLiveData = _productCategoryDataSuccessfullyReadMutableLiveData;
    private final MutableLiveData<Throwable> _errorReadingProductCategoryDataMutableLiveData = new MutableLiveData<>();
    LiveData<Throwable> errorReadingProductCategoryDataLiveData = _errorReadingProductCategoryDataMutableLiveData;
    private final MutableLiveData<ProductList> _productListDataGetSuccessfullyMutableLiveData = new MutableLiveData<>();
    LiveData<ProductList> productListDataGetSuccessfullyLiveData = _productListDataGetSuccessfullyMutableLiveData;
    private final MutableLiveData<Throwable> _errorReadingProductListDataLiveDataMutableLiveData = new MutableLiveData<>();
    LiveData<Throwable> errorReadingProductListDataLiveData = _errorReadingProductListDataLiveDataMutableLiveData;
    private final MutableLiveData<ProductList> _researchedProductsSuccessfullyReadMutableLiveData = new MutableLiveData<>();
    LiveData<ProductList> researchedProductsSuccessfullyReadLiveData = _researchedProductsSuccessfullyReadMutableLiveData;

    @Inject
    public HomeViewModel(GetProductCategories getProductCategories, GetProducts getProducts, GetSearchedProducts getSearchedProducts) {
        this.getProductCategories = getProductCategories;
        this.getProducts = getProducts;
        this.getSearchedProducts = getSearchedProducts;
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

    public void getSearchedProducts(CharSequence productName) {
        Function<Result<ProductList>, Void> callback = productListResult -> {
            if (productListResult instanceof Result.Success) {
                _researchedProductsSuccessfullyReadMutableLiveData.postValue(((Result.Success<ProductList>) productListResult).getData());
            } else if (productListResult instanceof Result.Error) {
                _errorReadingProductCategoryDataMutableLiveData.postValue(((Result.Error<ProductList>) productListResult).getValue());
            }
            return null;
        };
        getSearchedProducts.call(callback, productName.toString());
    }
}
