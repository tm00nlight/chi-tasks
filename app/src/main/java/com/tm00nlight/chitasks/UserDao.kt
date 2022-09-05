package com.tm00nlight.chitasks

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface UserDao {
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)

    @Query("delete from user")
    fun deleteAllUsers()

    @Query("select * from user order by name asc")
    fun getAllUsers(): LiveData<List<User>>

    @Query("select * from user order by age asc")
    fun getAllUsersByAge(): LiveData<List<User>>

    @Query("select * from user order by isStudent desc")
    fun getAllUsersByStudents(): LiveData<List<User>>
}