package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;

public class DashboardActivity extends AppCompatActivity {

    private MapView mapView;
    private Button btnToggleMap;
    private Button btnSaveToSDCard;
    private Button btnLoadFromSDCard;
    private ImageView searchIcon;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize the MapView
        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);
        mapView.onResume(); // Needed to get the map to display immediately
        MapsInitializer.initialize(this);

        // Initialize buttons
        btnToggleMap = findViewById(R.id.btnToggle);
        btnSaveToSDCard = findViewById(R.id.btnSaveToStorage);
        btnLoadFromSDCard = findViewById(R.id.btnLoadFromStorage);
        searchIcon = findViewById(R.id.tvStorageContent);

        // Set up button click listeners
        btnToggleMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mapView.getVisibility() == View.GONE) {
                    mapView.setVisibility(View.VISIBLE);
                } else {
                    mapView.setVisibility(View.GONE);
                }
            }
        });

            // Check if user is authenticated
            if (!isUserAuthenticated()) {
                Intent intent = new Intent(this, login.class);
                startActivity(intent);
                finish();
                return;
            }
            setContentView(R.layout.activity_dashboard);
            // Other initialization code



        btnSaveToSDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your save to SD card functionality here
            }
        });

        btnLoadFromSDCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your load from SD card functionality here
            }
        });

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your search functionality here
            }
        });
    }


    private boolean isUserAuthenticated() {
        return false;

    }

    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }
}
