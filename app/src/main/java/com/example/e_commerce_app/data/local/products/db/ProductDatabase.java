package com.example.e_commerce_app.data.local.products.db;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.e_commerce_app.data.local.products.model.ProductEntity;

@Database(entities = {ProductEntity.class}, version = 1, exportSchema = false)
public abstract class ProductDatabase extends RoomDatabase {
    public abstract ProductDao productDao();
}
