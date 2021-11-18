package com.rex.project.helep.view.activities.register

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.project.helep.MainRepository
import com.rex.project.helep.local.entities.User
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val registeredId = MutableLiveData<Long>(-1)

    fun register(user: User) = viewModelScope.launch {
        registeredId.value = mainRepository.insertUser(user)
    }

    fun setLoggedIn(id: Long) {
        sharedPreferences.edit().putLong("loggedIn", id).apply()
    }

    fun getRegisteredId(): LiveData<Long> = registeredId
}