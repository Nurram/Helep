package com.rex.project.helep.view.activities.addTask

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository
import com.rex.project.helep.local.entities.Task
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class AddTaskViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)

    fun postTask(task: Task) = runBlocking(Dispatchers.IO) { mainRepository.insertTask(task) }
}