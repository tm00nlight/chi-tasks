package com.tm00nlight.chitasks


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository.get()
    var users: LiveData<List<User>> = getUsers()

    fun clearDB() {
        userRepository.deleteAllUsers()
    }

    fun saveUser(user: User) {
        userRepository.insert(user)
    }

    fun updateUser(user: User) {
        userRepository.update(user)
    }

    fun createUser(name: String, age: Int) {
        val newUser = User(name,age)
        userRepository.insert(newUser)
        //users += newUser
    }

    @JvmName("getUsersFun")
    fun getUsers(): LiveData<List<User>> = userRepository.getAllUsers()
}