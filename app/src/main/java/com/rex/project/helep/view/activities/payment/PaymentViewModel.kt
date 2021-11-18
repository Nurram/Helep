package com.rex.project.helep.view.activities.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rex.project.helep.MainRepository
import kotlinx.coroutines.launch

class PaymentViewModel(
    private val mainRepository: MainRepository
): ViewModel() {

    fun getTaskById(id: Long) = mainRepository.getTaskById(id)

    fun updateTaskStatus(id: Long, winnerId: Long, status: String) = viewModelScope.launch {
        mainRepository.updateTaskStatus(id, winnerId, status)
    }
}