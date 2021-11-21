package com.rex.project.helep.view.activities.payment

import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class PaymentViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getTaskById(id: Long) = mainRepository.getTaskById(id)

    fun updateTaskStatus(id: Long, winnerId: Long, status: String) = runBlocking(Dispatchers.IO) {
        mainRepository.updateTaskStatus(id, winnerId, status)
    }
}