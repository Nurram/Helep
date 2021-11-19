package com.rex.project.helep.view

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.MainRepository
import com.rex.project.helep.local.MainDb
import com.rex.project.helep.network.RetrofitInstance
import com.rex.project.helep.view.activities.addTask.AddTaskViewModel
import com.rex.project.helep.view.activities.login.LoginViewModel
import com.rex.project.helep.view.activities.payment.PaymentViewModel
import com.rex.project.helep.view.activities.register.RegisterViewModel
import com.rex.project.helep.view.activities.taskDone.TaskDoneViewModel
import com.rex.project.helep.view.activities.topUp.TopUpViewModel
import com.rex.project.helep.view.activities.viewProgress.ViewProgressViewModel
import com.rex.project.helep.view.activities.viewProgressFind.ViewProgressFindViewModel
import com.rex.project.helep.view.fragments.dashboard.DashboardViewModel
import com.rex.project.helep.view.fragments.find.FindViewModel
import com.rex.project.helep.view.fragments.posts.PostViewModel
import com.rex.project.helep.view.fragments.profile.ProfileViewModel

class ViewModelFactory(application: Application): ViewModelProvider.NewInstanceFactory() {

    private val mainDb = MainDb.getDb(application)
    private val apiInterface = RetrofitInstance.instance.apiInterface
    private val mainRepository = MainRepository(
        mainDb?.userDao,
        mainDb?.taskDao,
        apiInterface,
        mainDb?.walletDao,
    )
    private val sharedPreference = application.getSharedPreferences("auth", MODE_PRIVATE)

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(RegisterViewModel::class.java) ->
                RegisterViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) ->
                LoginViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(DashboardViewModel::class.java) ->
                DashboardViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(TopUpViewModel::class.java) ->
                TopUpViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(AddTaskViewModel::class.java) ->
                AddTaskViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(PostViewModel::class.java) ->
                PostViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(PaymentViewModel::class.java) ->
                PaymentViewModel(mainRepository) as T
            modelClass.isAssignableFrom(TaskDoneViewModel::class.java) ->
                TaskDoneViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(ViewProgressViewModel::class.java) ->
                ViewProgressViewModel(mainRepository) as T
            modelClass.isAssignableFrom(ViewProgressFindViewModel::class.java) ->
                ViewProgressFindViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(FindViewModel::class.java) ->
                FindViewModel(mainRepository, sharedPreference) as T
            modelClass.isAssignableFrom(ProfileViewModel::class.java) ->
                ProfileViewModel(sharedPreference) as T
            else -> RegisterViewModel(mainRepository, sharedPreference) as T
        }
    }
}