package com.rex.project.helep.view.activities.editProfile

import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.rex.project.helep.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking

class EditProfileViewModel(
    private val mainRepository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private fun getLoggedIn() = sharedPreferences.getLong("loggedIn", -1)

    fun getUserById() = mainRepository.getUserById(getLoggedIn())

    private val result = MutableLiveData<Int>(-1)
    fun updateUserNameAndName(username: String, name: String) = runBlocking(Dispatchers.IO) {
        result.postValue(mainRepository.updateUserNameAndName(getLoggedIn(), username, name))
    }

    fun getResult(): LiveData<Int> = result
}