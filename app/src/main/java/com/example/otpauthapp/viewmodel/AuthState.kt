package com.example.otpauthapp.viewmodel

data class AuthState(

    val email: String = "",
    val otp: String = "",

    val isOtpSent: Boolean = false,
    val isLoggedIn: Boolean = false,

    val error: String = "",


    val sessionStart: Long = 0L,


    val showHistory: Boolean = false,


    val history: List<Long> = emptyList()
)
