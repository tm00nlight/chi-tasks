package com.tm00nlight.chitasks

import android.app.Application
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras.Empty.get
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    var users: List<User>
    private val userRepository = UserRepository.get()

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

    fun clearDB() {
        userRepository.deleteAllUsers()
    }

    fun saveUser(user: User) {
        userRepository.insert(user)
    }

}