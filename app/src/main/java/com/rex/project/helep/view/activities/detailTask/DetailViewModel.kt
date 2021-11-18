package com.rex.project.helep.view.activities.detailTask

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.project.helep.MainRepository
import com.rex.project.helep.local.entities.Bidding
import com.rex.project.helep.local.entities.Task
import kotlinx.coroutines.launch

class DetailViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    fun insertBidding(bidding: Bidding) {
        viewModelScope.launch { mainRepository.insertBidding(bidding) }
    }

    fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)
}