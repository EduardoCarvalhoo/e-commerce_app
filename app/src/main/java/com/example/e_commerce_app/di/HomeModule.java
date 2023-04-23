package com.example.e_commerce_app.di;

import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsApiDataSource;
import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsApiDataSourceImpl;
import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsCategoryApiDataSource;
import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsCategoryApiDataSourceImpl;
import com.example.e_commerce_app.data.repository.ProductsCategoryRepository;
import com.example.e_commerce_app.data.repository.ProductsCategoryRepositoryImpl;
import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.data.repository.ProductsRepositoryImpl;
import com.example.e_commerce_app.domain.useCase.GetProductCategories;
import com.example.e_commerce_app.domain.useCase.GetProductCategoriesImpl;
import com.example.e_commerce_app.domain.useCase.GetProducts;
import com.example.e_commerce_app.domain.useCase.GetProductsImpl;

import dagger.Binds;
import dagger.Module;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public abstract class HomeModule {
    @Binds
    public abstract GetProductCategories provideGetProductList(GetProductCategoriesImpl impl);

    @Binds
    public abstract ProductsCategoryRepository provideProductsRepository(ProductsCategoryRepositoryImpl impl);

    @Binds
    public abstract ProductsCategoryApiDataSource provideProductsApiDataSource(ProductsCategoryApiDataSourceImpl impl);

    @Binds
    public abstract GetProducts getProducts(GetProductsImpl impl);

    @Binds
    public abstract ProductsRepository productsRepository(ProductsRepositoryImpl impl);

    @Binds
    public abstract ProductsApiDataSource productsApiDataSource(ProductsApiDataSourceImpl impl);
}
