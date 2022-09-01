package com.tm00nlight.chitasks

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [User::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
/*
    companion object {
        private var instance: UserDatabase? = null

        @Synchronized
        fun getInstance(ctx: Context): UserDatabase {
            if(instance == null)
                instance = Room.databaseBuilder(ctx.applicationContext, UserDatabase::class.java,
                    "users_db")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build()

            return instance!!

        }

        private val roomCallback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                populateDatabase(instance!!)
            }
        }

        private fun populateDatabase(db: UserDatabase) {
            val userDao = db.userDao()
            /*userDao.insert(User("Sveta 1", 17, true))
            userDao.insert(User("Sveta 2", 14))
            userDao.insert(User("Sveta 3", 80))
            userDao.insert(User("Sveta 4", 20))
            userDao.insert(User("Sveta 5", 18))
            userDao.insert(User("Sveta 6", 22))
            userDao.insert(User("Sveta 7", 25))*/
        }

    }
*/
}