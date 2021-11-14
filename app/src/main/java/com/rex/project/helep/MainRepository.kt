package com.rex.project.helep

import com.rex.project.helep.local.daos.UserDao
import com.rex.project.helep.local.entities.User

class MainRepository (
    private val userDao: UserDao?
) {
    suspend fun insertUser(user: User) = userDao?.insert(user)

    suspend fun updateUser(user: User) = userDao?.update(user)

    fun getUserByEmailPassword(email: String, password: String) =
        userDao?.getUserByEmailPassword(email, password)
}