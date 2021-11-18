package com.rex.project.helep.view.fragments.profile

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel

class ProfileViewModel(
    private val sharedPreferences: SharedPreferences
): ViewModel() {

    fun logout() { sharedPreferences.edit().remove("loggedIn").apply() }
}