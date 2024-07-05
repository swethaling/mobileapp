package com.example.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;


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

        productImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start ProductActivity
                Intent intent = new Intent(Shop.this, ProductActivity.class);
                startActivity(intent);
            }
        });
        productImageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start ProductActivity
                Intent intent = new Intent(Shop.this, ProductActivity2.class);
                startActivity(intent);
            }
        });
        productImageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start ProductActivity
                Intent intent = new Intent(Shop.this, ProductActivity3.class);
                startActivity(intent);
            }
        });
        productImageView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent to start ProductActivity
                Intent intent = new Intent(Shop.this, ProductActivity4.class);
                startActivity(intent);
            }
        });

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
            // Handle home action
            startActivity(new Intent(Shop.this, home.class));
            return true;
        } else if (itemId == R.id.navigation_dashboard) {
            // Handle dashboard action
            startActivity(new Intent(Shop.this, DashboardActivity.class));
            return true;
        } else if (itemId == R.id.navigation_notifications) {
            // Handle notification action
            startActivity(new Intent(Shop.this, NotificationActivity.class));
            return true;
        }
        return false;
    }
}
