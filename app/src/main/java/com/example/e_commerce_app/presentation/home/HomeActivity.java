package com.example.e_commerce_app.presentation.home;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import com.example.e_commerce_app.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}