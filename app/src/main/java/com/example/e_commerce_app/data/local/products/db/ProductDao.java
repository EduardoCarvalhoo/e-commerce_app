package com.example.e_commerce_app.data.local.products.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.e_commerce_app.data.local.products.model.ProductEntity;

import java.util.List;

@Dao
public interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ProductEntity product);

    @Delete
    void delete(ProductEntity product);

    @Query("Select * FROM product_table")
    List<ProductEntity> getAllProducts();
}
