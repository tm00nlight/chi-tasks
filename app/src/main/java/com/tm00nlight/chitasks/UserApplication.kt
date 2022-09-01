package com.tm00nlight.chitasks

import android.app.Application

class UserApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        UserRepository.initialize(this)
    }
}