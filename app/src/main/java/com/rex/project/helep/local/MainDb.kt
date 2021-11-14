package com.rex.project.helep.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rex.project.helep.local.daos.UserDao
import com.rex.project.helep.local.entities.User

@Database(entities = [User::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract val userDao: UserDao

    companion object {
        @Volatile
        private var db: MainDb? = null

        fun getDb(application: Application): MainDb? {
            if (db == null) {
                synchronized(MainDb::class.java) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            application.applicationContext,
                            MainDb::class.java, "helep_db"
                        )
                            .build()
                    }
                }
            }

            return db
        }
    }
}