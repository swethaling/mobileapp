package com.example.myapplication;


import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class ProductActivity2 extends AppCompatActivity {

    private static final String CHANNEL_ID = "order_channel";

    private ImageView productImageView;
    private TextView productNameTextView, productPriceTextView, cartDetailsTextView, paymentDetailsTextView;
    private Button addToCartButton, payButton;

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

        // Set product details (these can be fetched from a database or passed via Intent)
        productImageView.setImageResource(R.drawable.slide2);
        productNameTextView.setText("COLT SPORTS");
        productPriceTextView.setText("₹ 7,420.00");

        addToCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Add product to cart (this can be saved to local database or shared preferences)
                cartDetailsTextView.setText("Product Name added to cart");
                Toast.makeText(ProductActivity2.this, "Product added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Process payment (this can be done via payment gateway integration)
                paymentDetailsTextView.setText("Payment successful for Product Name");
                Toast.makeText(ProductActivity2.this, "Order placed", Toast.LENGTH_SHORT).show();
                sendNotification();
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
}
