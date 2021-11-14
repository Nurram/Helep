package com.rex.project.helep.view.activities.register

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.project.helep.MainRepository
import com.rex.project.helep.local.entities.User
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    fun register(user: User) = viewModelScope.launch {
        mainRepository.insertUser(user)
        setLoggedIn()
    }

    private fun setLoggedIn() {
        sharedPreferences.edit().putBoolean("loggedIn", true).apply()
    }
}