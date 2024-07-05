package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class home extends AppCompatActivity {

    private FirebaseFirestore db;

    private EditText etName, etAge, etAddress;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale, rbOther;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize Firestore
        db = FirebaseFirestore.getInstance();

        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etAddress = findViewById(R.id.etAddress);
        rgGender = findViewById(R.id.rgGender);
        rbMale = findViewById(R.id.rbMale);
        rbFemale = findViewById(R.id.rbFemale);
        rbOther = findViewById(R.id.rbOther);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                collectUserData();
            }
        });
    }

    private void collectUserData() {
        String name = etName.getText().toString();
        String age = etAge.getText().toString();
        String address = etAddress.getText().toString();
        String gender = "";

        int selectedGenderId = rgGender.getCheckedRadioButtonId();
        if (selectedGenderId == rbMale.getId()) {
            gender = "Male";
        } else if (selectedGenderId == rbFemale.getId()) {
            gender = "Female";
        } else if (selectedGenderId == rbOther.getId()) {
            gender = "Other";
        }

        if (name.isEmpty() || age.isEmpty() || address.isEmpty() || gender.isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else {
            // Create a new user object
            Map<String, Object> user = new HashMap<>();
            user.put("name", name);
            user.put("age", age);
            user.put("address", address);
            user.put("gender", gender);

            // Add a new document with a generated ID
            db.collection("users")
                    .add(user)
                    .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentReference> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(home.this, "User data saved successfully", Toast.LENGTH_SHORT).show();
                                // Clear input fields after successful submission
                                etName.setText("");
                                etAge.setText("");
                                etAddress.setText("");
                                rgGender.clearCheck(); // Clear selected radio button
                            } else {
                                Toast.makeText(home.this, "Error saving user data", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}
