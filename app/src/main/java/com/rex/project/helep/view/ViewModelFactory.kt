package com.rex.project.helep.view

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.MainRepository
import com.rex.project.helep.local.MainDb
import com.rex.project.helep.view.activities.login.LoginViewModel
import com.rex.project.helep.view.activities.register.RegisterViewModel

class ViewModelFactory(application: Application): ViewModelProvider.NewInstanceFactory() {

    private val mainDb = MainDb.getDb(application)
    private val mainRepository = MainRepository(mainDb?.userDao)
    private val sharedPreference = application.getSharedPreferences("auth", MODE_PRIVATE)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) ->
                RegisterViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(mainRepository, sharedPreference) as T
            else -> RegisterViewModel(mainRepository, sharedPreference) as T
        }
    }
}