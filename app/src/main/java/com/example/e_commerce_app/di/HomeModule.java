package com.example.e_commerce_app.di;

import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsCategoryApiDataSource;
import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsCategoryApiDataSourceImpl;
import com.example.e_commerce_app.data.repository.ProductsCategoryRepository;
import com.example.e_commerce_app.data.repository.ProductsCategoryRepositoryImpl;
import com.example.e_commerce_app.domain.useCase.GetProductCategory;
import com.example.e_commerce_app.domain.useCase.GetProductCategoryImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class HomeModule {
    @Binds
    public abstract GetProductCategory provideGetProductList(GetProductCategoryImpl impl);

    @Binds
    public abstract ProductsCategoryRepository provideProductsRepository(ProductsCategoryRepositoryImpl impl);

    @Binds
    public abstract ProductsCategoryApiDataSource provideProductsApiDataSource(ProductsCategoryApiDataSourceImpl impl);
}
