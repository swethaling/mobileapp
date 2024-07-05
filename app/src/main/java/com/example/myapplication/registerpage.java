package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class registerpage extends AppCompatActivity {

    private EditText usernameEditText;
    private EditText passwordEditText;
    private EditText confirmPasswordEditText;
    private Button loginButton;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerpage); // Make sure this matches your XML filename

        // Initialize views
        usernameEditText = findViewById(R.id.email);
        mAuth = FirebaseAuth.getInstance();
        passwordEditText = findViewById(R.id.password);
        confirmPasswordEditText = findViewById(R.id.confirmpassword);
        loginButton = findViewById(R.id.loginButton);

        // Set click listener for the sign-up button
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = usernameEditText.getText().toString().trim();
                String password = passwordEditText.getText().toString().trim();
                String confirmPassword = confirmPasswordEditText.getText().toString().trim();

                // Check if the input is valid
                if (validateInput(email, password, confirmPassword)) {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        Toast.makeText(registerpage.this, "Registration successful", Toast.LENGTH_SHORT).show();
                                        // Navigate to the Shop activity
                                        Intent intent = new Intent(getApplicationContext(), Shop.class);
                                        startActivity(intent);
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        String errorMessage = task.getException().getMessage();
                                        Toast.makeText(registerpage.this, "Authentication Failed: " + errorMessage, Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                } else {
                    // Show error message
                    Toast.makeText(registerpage.this, "Invalid input", Toast.LENGTH_SHORT).show();
                }
            }
        });

     usernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
         @Override
         public void onFocusChange(View v, boolean hasFocus) {
             if(hasFocus){
                 Toast.makeText(registerpage.this,"ENTER EMAIL",Toast.LENGTH_SHORT).show();
             }
         }
     });
    }

    // Validate input fields
    private boolean validateInput(String email, String password, String confirmPassword) {
        if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            return false;
        }
        if (!password.equals(confirmPassword)) {
            return false;
        }
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast.makeText(this, "Invalid email format", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
