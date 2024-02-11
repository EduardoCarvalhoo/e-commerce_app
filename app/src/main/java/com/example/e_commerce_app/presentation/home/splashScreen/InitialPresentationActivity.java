package com.example.e_commerce_app.presentation.home.splashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.e_commerce_app.R;
import com.example.e_commerce_app.databinding.ActivityInitialPresentationBinding;
import com.example.e_commerce_app.presentation.home.activity.HomeActivity;

public class InitialPresentationActivity extends AppCompatActivity {
    private ActivityInitialPresentationBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInitialPresentationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setupSplashScreen();
    }

    public void setupSplashScreen() {
        Animation slideAnimation = AnimationUtils.loadAnimation(this, R.anim.side_slide);
        binding.initialPresentationLogoSplashScreen.startAnimation(slideAnimation);

        final Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(InitialPresentationActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, 3000);
    }
}