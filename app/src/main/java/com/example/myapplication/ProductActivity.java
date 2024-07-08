package com.example.myapplication;

import android.Manifest;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;

public class ProductActivity extends AppCompatActivity {

    private static final String CHANNEL_ID = "order_channel";
    private static final int REQUEST_WRITE_STORAGE = 112;

    private ImageView productImageView;
    private TextView productNameTextView, productPriceTextView, cartDetailsTextView, paymentDetailsTextView;
    private Button addToCartButton, payButton, saveImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product1);

        productImageView = findViewById(R.id.productImageView);
        productNameTextView = findViewById(R.id.productNameTextView);
        productPriceTextView = findViewById(R.id.productPriceTextView);
        cartDetailsTextView = findViewById(R.id.cartDetailsTextView);
        paymentDetailsTextView = findViewById(R.id.paymentDetailsTextView);
        addToCartButton = findViewById(R.id.addToCartButton);
        payButton = findViewById(R.id.payButton);
        saveImageButton = findViewById(R.id.saveImageButton);

        // Set product details
        productImageView.setImageResource(R.drawable.slide1);
        productNameTextView.setText("Lady Bird");
        productPriceTextView.setText("₹ 5,420.00");

        addToCartButton.setOnClickListener(v -> {
            cartDetailsTextView.setText("Product Name added to cart");
            Toast.makeText(ProductActivity.this, "Product added to cart", Toast.LENGTH_SHORT).show();
        });

        payButton.setOnClickListener(v -> {
            paymentDetailsTextView.setText("Payment successful for Product Name");
            Toast.makeText(ProductActivity.this, "Order placed", Toast.LENGTH_SHORT).show();
            sendNotification();
        });

        saveImageButton.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(ProductActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(ProductActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_STORAGE);
            } else {
                saveImageToInternalStorage();
            }
        });

        createNotificationChannel();
    }

    private void sendNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notifications) // Update this with your actual icon
                .setContentTitle("Order Placed")
                .setContentText("ORDER PLACED THANK YOU")
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setAutoCancel(true);

        notificationManager.notify(1, builder.build());
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "Order Notifications";
            String description = "Channel for order notifications";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void saveImageToInternalStorage() {
        BitmapDrawable drawable = (BitmapDrawable) productImageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();

        try (FileOutputStream fos = new FileOutputStream(new File(getFilesDir(), "product_image_" + System.currentTimeMillis() + ".jpg"))) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
            Toast.makeText(this, "Image saved to internal storage", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Failed to save image: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_WRITE_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                saveImageToInternalStorage();
            } else {
                Toast.makeText(this, "Permission denied to write to external storage", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
