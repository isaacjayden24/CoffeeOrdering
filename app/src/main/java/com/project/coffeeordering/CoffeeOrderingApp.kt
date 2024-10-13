package com.project.coffeeordering

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp

class CoffeeOrderingApp : Application() {
    override fun onCreate() {
        super.onCreate()
        try {
            FirebaseApp.initializeApp(this)
            Log.d("Firebase", "Firebase initialized successfully")
        } catch (e: Exception) {
            Log.e("Firebase", "Failed to initialize Firebase: ${e.message}")
        }
    }
}