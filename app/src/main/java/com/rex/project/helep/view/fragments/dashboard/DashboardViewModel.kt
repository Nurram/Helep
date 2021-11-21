package com.rex.project.helep.view.fragments.dashboard

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository

class DashboardViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {

    private fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)

    fun getWalletByUserId() = mainRepository.getWalletByUserId(getLoggedIn())
}