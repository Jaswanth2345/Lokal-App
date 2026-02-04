package com.example.otpauthapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.otpauthapp.data.SessionStorage

class AuthViewModelFactory(
    private val context: Context
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {

            val storage = SessionStorage(context)

            return AuthViewModel(storage) as T
        }

        throw IllegalArgumentException("Unknown ViewModel")
    }
}
