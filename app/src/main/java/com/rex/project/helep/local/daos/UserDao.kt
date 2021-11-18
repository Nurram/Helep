package com.rex.project.helep.local.daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.rex.project.helep.local.entities.User

@Dao
interface UserDao {
    @Insert
    suspend fun insert(user: User): Long

    @Update
    suspend fun update(user: User)

    @Query("SELECT * FROM user WHERE email=:email AND password=:password")
    fun getUserByEmailPassword(email: String, password: String): LiveData<List<User>>

    @Query("SELECT * FROM user WHERE email=:email")
    fun getUserByEmail(email: String): LiveData<List<User>>
}