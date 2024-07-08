package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity {

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Check if user is authenticated
        if (!isUserAuthenticated()) {
            Intent intent = new Intent(this, login.class);
            startActivity(intent);
            finish();
            return;
        }

        setContentView(R.layout.activity_dashboard);


        Button btnSaveToStorage = findViewById(R.id.btnSaveToStorage);
        Button btnLoadFromStorage = findViewById(R.id.btnLoadFromStorage);
        ImageView storageContent = findViewById(R.id.tvStorageContent);

        
        btnSaveToStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your save to storage functionality here
            }
        });

        btnLoadFromStorage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your load from storage functionality here
            }
        });

        storageContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add your storage content display functionality here
            }
        });
    }

    private boolean isUserAuthenticated() {
        // Add your actual authentication check logic here
        return false;
    }
}
