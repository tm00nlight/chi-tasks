package com.tm00nlight.chitasks


import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class UserViewModel : ViewModel() {
    private val userRepository = UserRepository.get()
    var users: LiveData<List<User>> = getUsers()
    var users2: LiveData<List<User>> = getUsersByAge()
    var users3: LiveData<List<User>> = getUsersByStudents()

    fun clearDB() {
        userRepository.deleteAllUsers()
    }

    fun saveUser(user: User) {
        userRepository.insert(user)
    }

    fun updateUser(user: User) {
        userRepository.update(user)
    }

    fun deleteUser(user: User) {
        userRepository.delete(user)
    }

    fun createUser(name: String, age: Int) {
        val newUser = User(name,age)
        userRepository.insert(newUser)
        //users += newUser
    }

    @JvmName("getUsersFun")
    fun getUsers(): LiveData<List<User>> = userRepository.getAllUsers()
    fun getUsersByAge(): LiveData<List<User>> = userRepository.getAllUsersByAge()
    fun getUsersByStudents(): LiveData<List<User>> = userRepository.getAllUsersByStudents()
}