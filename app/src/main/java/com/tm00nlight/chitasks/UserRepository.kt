package com.tm00nlight.chitasks

import android.app.Application
import androidx.lifecycle.LiveData

class UserRepository(app: Application){
    private val database = UserDatabase.getInstance(app)
    private val userDao = database.userDao()

    fun getAllUsers(): List<User> = userDao.getAllUsers()
    fun insert(user: User) = userDao.insert(user)
    fun update(user: User) = userDao.update(user)
    fun delete(user: User) = userDao.delete(user)
    fun deleteAllUsers() = userDao.deleteAllUsers()

}