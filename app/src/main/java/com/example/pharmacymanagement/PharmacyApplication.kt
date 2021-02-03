package com.example.pharmacymanagement

import android.app.Application
import timber.log.Timber

class PharmacyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        // adding Timber lib for logging
        Timber.plant(Timber.DebugTree())
    }
}