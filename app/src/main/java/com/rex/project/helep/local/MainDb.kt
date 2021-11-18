package com.rex.project.helep.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rex.project.helep.local.daos.BiddingDao
import com.rex.project.helep.local.daos.TaskDao
import com.rex.project.helep.local.daos.UserDao
import com.rex.project.helep.local.entities.Bidding
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.local.entities.User
import com.rex.project.helep.utils.Constants

@Database(entities = [User::class, Task::class, Bidding::class], version = 1)
abstract class MainDb : RoomDatabase() {
    abstract val userDao: UserDao
    abstract val taskDao: TaskDao
    abstract val biddingDao: BiddingDao

    companion object {
        @Volatile
        private var db: MainDb? = null

        fun getDb(application: Application): MainDb? {
            if (db == null) {
                synchronized(MainDb::class.java) {
                    if (db == null) {
                        db = Room.databaseBuilder(
                            application.applicationContext,
                            MainDb::class.java, Constants.DB_NAME
                        )
                            .build()
                    }
                }
            }

            return db
        }
    }
}