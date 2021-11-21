package com.rex.project.helep.view.fragments.profile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository

class ProfileViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun logout() {
        sharedPreferences.edit().remove("loggedIn").apply()
    }

    fun getUserById() = mainRepository.getUserById(getLoggedIn())

    private fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)
}