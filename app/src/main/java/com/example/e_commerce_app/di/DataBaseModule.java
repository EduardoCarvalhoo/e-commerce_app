package com.example.e_commerce_app.di;

import android.app.Application;

import androidx.room.Room;

import com.example.e_commerce_app.data.local.products.db.ProductDao;
import com.example.e_commerce_app.data.local.products.db.ProductDatabase;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ViewModelComponent;

@Module
@InstallIn(ViewModelComponent.class)
public class DataBaseModule {

    @Provides
    public static ProductDatabase productDatabase(Application application) {
        return Room.databaseBuilder(application, ProductDatabase.class, "my_database")
                .fallbackToDestructiveMigration().allowMainThreadQueries()
                .build();
    }

    @Provides
    public static ProductDao productDao(ProductDatabase productDatabase) {
        return productDatabase.productDao();
    }
}
