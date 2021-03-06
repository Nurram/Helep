package com.rex.project.helep.view.activities.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository

class LoginViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun login(email: String, password: String) =
        mainRepository.getUserByEmailPassword(email, password)

    fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)

    fun setLoggedIn(id: Long) {
        sharedPreferences.edit().putLong("loggedIn", id).apply()
    }
}