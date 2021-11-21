package com.rex.project.helep.view.activities.taskDone

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class TaskDoneViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun spendWallet(value: Long) = runBlocking(Dispatchers.IO) {
        mainRepository.spendWallet(getLoggedIn(), value)
    }

    private fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)
}