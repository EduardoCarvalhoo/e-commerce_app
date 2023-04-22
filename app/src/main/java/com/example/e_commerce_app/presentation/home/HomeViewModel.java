package com.example.e_commerce_app.presentation.home;

import androidx.lifecycle.ViewModel;

import com.example.e_commerce_app.domain.useCase.GetProductCategory;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    private GetProductCategory getProductCategory;

    @Inject
    public HomeViewModel(GetProductCategory getProductCategory) {
        this.getProductCategory = getProductCategory;
    }

    public void getProductCategory(){
        getProductCategory.call();
    }
}
