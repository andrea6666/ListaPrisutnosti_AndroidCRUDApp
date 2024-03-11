package com.example.listaprisutnosti;

import android.app.Application;
import com.google.firebase.FirebaseApp;

/**
 * Created by: Andrea
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize Firebase
        FirebaseApp.initializeApp(this);
    }
}
