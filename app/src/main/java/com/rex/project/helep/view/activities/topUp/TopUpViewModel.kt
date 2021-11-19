package com.rex.project.helep.view.activities.topUp

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.project.helep.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class TopUpViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    private fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)

    fun topupWallet(value: Long) = runBlocking(Dispatchers.IO) {
        mainRepository.topUpWallet(getLoggedIn(), value)
    }
}