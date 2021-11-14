package com.rex.project.helep.view.activities.login

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.project.helep.MainRepository
import kotlinx.coroutines.launch

class LoginViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    fun login(email: String, password: String) =
        mainRepository.getUserByEmailPassword(email, password)

    fun getLoggedIn() = sharedPreferences.getBoolean("loggedIn", false)

    fun setLoggedIn() {
        sharedPreferences.edit().putBoolean("loggedIn", true).apply()
    }
}