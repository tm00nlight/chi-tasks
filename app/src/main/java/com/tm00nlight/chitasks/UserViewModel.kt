package com.tm00nlight.chitasks

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    var users: List<User>

    init{
        users = listOf(
            User("Katrin Nelson", 17, true),
            User("Molly Davis", 14),
            User("Poppy Braxton", 80),
            User("Andrew Simpson", 20),
            User("Harry Potter", 18),
            User("Winney Ray", 22),
            User("Steven Arnolds", 25)
        )
        Log.d("ViewModel after rotate", users.toString())
    }
}