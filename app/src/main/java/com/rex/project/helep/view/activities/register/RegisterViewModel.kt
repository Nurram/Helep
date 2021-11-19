package com.rex.project.helep.view.activities.register

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.project.helep.MainRepository
import com.rex.project.helep.local.entities.User
import com.rex.project.helep.local.entities.Wallet
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RegisterViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {
    private val registeredId = MutableLiveData<Long>(-1)

    fun register(user: User) = runBlocking(Dispatchers.IO) {
        registeredId.postValue(mainRepository.insertUser(user))
    }

    fun addWallet(userId: Long) = runBlocking(Dispatchers.IO) {
        mainRepository.insertWallet(Wallet(0, userId))
    }
    fun setLoggedIn(id: Long) {
        sharedPreferences.edit().putLong("loggedIn", id).apply()
    }

    fun getRegisteredId(): LiveData<Long> = registeredId
}