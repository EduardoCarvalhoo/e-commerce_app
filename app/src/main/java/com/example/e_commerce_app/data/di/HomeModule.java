package com.example.e_commerce_app.data.di;

import static com.example.e_commerce_app.utils.ConstantsConfiguration.BASE_URL;

import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsApiDataSource;
import com.example.e_commerce_app.data.remote.productsCategory.dataSource.ProductsApiDataSourceImpl;
import com.example.e_commerce_app.data.remote.rest.ProductsApiService;
import com.example.e_commerce_app.data.repository.ProductsRepository;
import com.example.e_commerce_app.data.repository.ProductsRepositoryImpl;
import com.example.e_commerce_app.domain.useCase.GetProductList;
import com.example.e_commerce_app.domain.useCase.GetProductListImpl;
import com.example.e_commerce_app.presentation.home.HomeViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
class RetrofitModule {
    @Provides
    @Singleton
    public ProductsApiService provideMovieApiService() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ProductsApiService.class);
    }

    @Module
    @InstallIn(ActivityComponent.class)
    public class HomeModule {
        @Provides
        public GetProductList provideGetProductList(ProductsRepository productsRepository) {
            return new GetProductListImpl(productsRepository);
        }

        @Provides
        public ProductsRepository provideProductsRepository(ProductsApiDataSource productsApiDataSource) {
            return new ProductsRepositoryImpl(productsApiDataSource);
        }

        @Provides
        public ProductsApiDataSource provideProductsApiDataSource(ProductsApiService service) {
            return new ProductsApiDataSourceImpl(service);
        }

        @Provides
        public HomeViewModel provideHomeViewModel(GetProductList getProductList) {
            return new HomeViewModel(getProductList);
        }
    }
}
