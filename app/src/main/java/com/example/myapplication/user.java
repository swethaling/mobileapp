package com.example.myapplication;
import java.util.HashMap;
import java.util.Map;

public class user {
    private String name;
    private String age;
    private String gender;
    private String address;

    public user() {
        // Default constructor required for Firestore
    }

    public user(String name, String age, String gender, String address) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.address = address;
    }

    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("age", age);
        result.put("gender", gender);
        result.put("address", address);
        return result;
    }
}

