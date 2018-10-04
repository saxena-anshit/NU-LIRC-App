package com.example.abdemanaaf.nulircapp;

import android.app.Application;

import com.firebase.client.Firebase;

public class NULIRCApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
