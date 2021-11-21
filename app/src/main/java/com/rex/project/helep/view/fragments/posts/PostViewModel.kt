package com.rex.project.helep.view.fragments.posts

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository

class PostViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun getPendingTaskByUserId() = mainRepository.getPendingTaskByUserId(getLoggedIn())

    fun getOnProgressTaskByUserId() = mainRepository.getOnProgressTaskByUserId(getLoggedIn())

    private fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)
}