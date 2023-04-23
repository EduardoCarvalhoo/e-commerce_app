package com.example.e_commerce_app.presentation.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.e_commerce_app.domain.model.ProductCategoryList;
import com.example.e_commerce_app.domain.result.Result;
import com.example.e_commerce_app.domain.useCase.GetProductCategory;

import java.util.List;
import java.util.function.Function;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private final GetProductCategory getProductCategory;

    private final MutableLiveData<List<String>> _productCategoryDataSuccessfullyReadMutableLiveData = new MutableLiveData<>();
    LiveData<List<String>> productCategoryDataSuccessfullyReadLiveData = _productCategoryDataSuccessfullyReadMutableLiveData;
    private final MutableLiveData<Exception> _errorReadingProductCategoryDataMutableLiveData = new MutableLiveData<>();
    LiveData<Exception> errorReadingProductCategoryDataLiveData = _errorReadingProductCategoryDataMutableLiveData;

    @Inject
    public HomeViewModel(GetProductCategory getProductCategory) {
        this.getProductCategory = getProductCategory;
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
        getProductCategory.call(callback);
    }
}
