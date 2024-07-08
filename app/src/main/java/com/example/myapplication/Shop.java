package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Shop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        ImageView productImageView = findViewById(R.id.imageView);
        ImageView productImageView2 = findViewById(R.id.imageView2);
        ImageView productImageView3 = findViewById(R.id.imageView3);
        ImageView productImageView4 = findViewById(R.id.imageView4);

        // Setting click listeners
        productImageView.setOnClickListener(v -> {
            Intent intent = new Intent(Shop.this, ProductActivity.class);
            startActivity(intent);
        });
        productImageView2.setOnClickListener(v -> {
            Intent intent = new Intent(Shop.this, ProductActivity2.class);
            startActivity(intent);
        });
        productImageView3.setOnClickListener(v -> {
            Intent intent = new Intent(Shop.this, ProductActivity3.class);
            startActivity(intent);
        });
        productImageView4.setOnClickListener(v -> {
            Intent intent = new Intent(Shop.this, ProductActivity4.class);
            startActivity(intent);
        });

        // Setting focus change listeners
        productImageView.setOnFocusChangeListener(this::onFocusChange);
        productImageView2.setOnFocusChangeListener(this::onFocusChange);
        productImageView3.setOnFocusChangeListener(this::onFocusChange);
        productImageView4.setOnFocusChangeListener(this::onFocusChange);

        // ImageSlider initialization
        ImageSlider imageSlider = findViewById(R.id.slide);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.slide1).toString(), ScaleTypes.FIT));
        slideModels.add(new SlideModel(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.slide2).toString(), ScaleTypes.FIT));
        slideModels.add(new SlideModel(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.slide3).toString(), ScaleTypes.FIT));

        if (imageSlider != null) {
            imageSlider.setImageList(slideModels);
        }

        // BottomNavigationView setup
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.navigation_home) {
            startActivity(new Intent(Shop.this, home.class));
            return true;
        } else if (itemId == R.id.navigation_dashboard) {
            startActivity(new Intent(Shop.this, DashboardActivity.class));
            return true;
        } else if (itemId == R.id.navigation_notifications) {
            startActivity(new Intent(Shop.this, NotificationActivity.class));
            return true;
        }
        return false;
    }

    private void onFocusChange(View view, boolean hasFocus) {
        if (hasFocus) {
            // Handle focus gained
            Toast.makeText(this, "ImageView focused!", Toast.LENGTH_SHORT).show();
        } else {
            // Handle focus lost
            Toast.makeText(this, "ImageView lost focus!", Toast.LENGTH_SHORT).show();
        }
    }
}
