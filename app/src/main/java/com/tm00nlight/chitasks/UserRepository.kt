package com.tm00nlight.chitasks

import android.app.Application
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

class UserRepository (context: Context){
    private val database = Room
        .databaseBuilder(context.applicationContext, UserDatabase::class.java, "users_db")
        .build()
    private val userDao = database.userDao()

    fun getAllUsers(): List<User> = userDao.getAllUsers()
    fun insert(user: User) = userDao.insert(user)
    fun update(user: User) = userDao.update(user)
    fun delete(user: User) = userDao.delete(user)
    fun deleteAllUsers() = userDao.deleteAllUsers()

    companion object{
        private var INSTANCE: UserRepository ?= null

        fun initialize(context: Context){
            if(INSTANCE == null) INSTANCE = UserRepository(context)
        }

        fun get(): UserRepository {
            return INSTANCE ?: throw IllegalStateException("UserRepository must be initialized")
        }
    }
}