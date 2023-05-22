package com.example.e_commerce_app.di;

import com.example.e_commerce_app.data.local.products.dataSource.ProductsLocalDataSource;
import com.example.e_commerce_app.data.local.products.dataSource.ProductsLocalDataSourceImpl;
import com.example.e_commerce_app.data.remote.products.dataSource.ProductsApiDataSource;
import com.example.e_commerce_app.data.remote.products.dataSource.ProductsApiDataSourceImpl;
import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.data.repository.ProductsRepositoryImpl;
import com.example.e_commerce_app.domain.useCase.GetProductCategories;
import com.example.e_commerce_app.domain.useCase.GetProductCategoriesImpl;
import com.example.e_commerce_app.domain.useCase.GetProductFavorite;
import com.example.e_commerce_app.domain.useCase.GetProductFavoriteImpl;
import com.example.e_commerce_app.domain.useCase.GetProducts;
import com.example.e_commerce_app.domain.useCase.GetProductsImpl;
import com.example.e_commerce_app.domain.useCase.GetSearchedProducts;
import com.example.e_commerce_app.domain.useCase.GetSearchedProductsImpl;
import com.example.e_commerce_app.domain.useCase.GetSingleProduct;
import com.example.e_commerce_app.domain.useCase.GetSingleProductImpl;
import com.example.e_commerce_app.domain.useCase.SetFavoriteProductDeletion;
import com.example.e_commerce_app.domain.useCase.SetFavoriteProductDeletionImpl;
import com.example.e_commerce_app.domain.useCase.SetFavoriteProductSave;
import com.example.e_commerce_app.domain.useCase.SetFavoriteProductSaveImpl;

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
    public abstract GetProducts getProducts(GetProductsImpl impl);

    @Binds
    public abstract ProductsRepository productsRepository(ProductsRepositoryImpl impl);

    @Binds
    public abstract ProductsApiDataSource productsApiDataSource(ProductsApiDataSourceImpl impl);

    @Binds
    public abstract GetSearchedProducts getSearchedProducts(GetSearchedProductsImpl impl);

    @Binds
    public abstract GetSingleProduct getSingleProduct(GetSingleProductImpl impl);

    @Binds
    public abstract SetFavoriteProductSave saveFavoriteProduct(SetFavoriteProductSaveImpl impl);

    @Binds
    public abstract ProductsLocalDataSource productsLocalDataSource(ProductsLocalDataSourceImpl impl);

    @Binds
    public abstract GetProductFavorite getProductFavorite(GetProductFavoriteImpl impl);

    @Binds
    public abstract SetFavoriteProductDeletion setFavoriteProductDeletion(SetFavoriteProductDeletionImpl impl);
}
