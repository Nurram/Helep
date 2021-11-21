package com.rex.project.helep.view.fragments.find

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository

class FindViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun getAllTasks() = mainRepository.getTaskAndUser(getLoggedIn())

    private fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)
}