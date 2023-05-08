package com.example.e_commerce_app.presentation.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce_app.domain.model.Product;
import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.model.ProductList;
import com.example.e_commerce_app.domain.result.Result;
import com.example.e_commerce_app.domain.useCase.GetProductCategories;
import com.example.e_commerce_app.domain.useCase.GetProducts;
import com.example.e_commerce_app.domain.useCase.GetSearchedProducts;
import com.example.e_commerce_app.domain.useCase.GetSingleProduct;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GetProductCategories getProductCategories;
    private final GetProducts getProducts;
    private final GetSearchedProducts getSearchedProducts;
    private final GetSingleProduct getSingleProduct;

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
    private final MutableLiveData<Product> _productDataSuccessfullyReadMutableLiveData = new MutableLiveData<>();
    public LiveData<Product> productDataSuccessfullyReadLiveData = _productDataSuccessfullyReadMutableLiveData;
    private final MutableLiveData<Throwable> _errorReadingProductDataMutableLiveData = new MutableLiveData<>();
    public LiveData<Throwable> errorReadingProductDataLiveData = _errorReadingProductDataMutableLiveData;

    @Inject
    public HomeViewModel(GetProductCategories getProductCategories, GetProducts getProducts, GetSearchedProducts getSearchedProducts, GetSingleProduct getSingleProduct) {
        this.getProductCategories = getProductCategories;
        this.getProducts = getProducts;
        this.getSearchedProducts = getSearchedProducts;
        this.getSingleProduct = getSingleProduct;
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

    public void getSingleProduct(String productId) {
        Function<Result<Product>, Void> callback = productResult -> {
            if (productResult instanceof Result.Success) {
                _productDataSuccessfullyReadMutableLiveData.postValue(((Result.Success<Product>) productResult).getData());
            } else if (productResult instanceof Result.Error) {
                _errorReadingProductDataMutableLiveData.postValue(((Result.Error<Product>) productResult).getValue());
            }
            return null;
        };
        getSingleProduct.call(callback, productId);
    }
}
