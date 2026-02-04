package com.example.otpauthapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.otpauthapp.analytics.AnalyticsLogger
import com.example.otpauthapp.data.OtpManager
import com.example.otpauthapp.data.SessionStorage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AuthViewModel(
    private val storage: SessionStorage
) : ViewModel() {

    private val _state = MutableStateFlow(AuthState())
    val state = _state.asStateFlow()


    init {


        val history = storage.getHistory()

        _state.value = _state.value.copy(
            history = history
        )
    }


    fun setEmail(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun setOtp(otp: String) {
        _state.value = _state.value.copy(otp = otp)
    }


    fun sendOtp() {

        val email = _state.value.email

        if (email.isBlank()) {
            setError("Enter email")
            return
        }

        val otp = OtpManager.generateOtp(email)

        AnalyticsLogger.logOtpGenerated(email, otp)

        _state.value = _state.value.copy(
            isOtpSent = true,
            error = ""
        )
    }


    fun verifyOtp() {

        val s = _state.value

        val result = OtpManager.validateOtp(
            s.email,
            s.otp
        )

        if (result == "Success") {

            AnalyticsLogger.logOtpSuccess(s.email)

            _state.value = s.copy(
                isLoggedIn = true,
                sessionStart = System.currentTimeMillis(),
                error = ""
            )

        } else {

            AnalyticsLogger.logOtpFailure(s.email)
            setError(result)
        }
    }



    fun logout() {

        val s = _state.value

        val end = System.currentTimeMillis()

        val duration =
            (end - s.sessionStart) / 1000   // seconds


        val newHistory =
            s.history + duration


        storage.saveHistory(newHistory)

        AnalyticsLogger.logLogout(s.email)

        _state.value = AuthState(
            history = newHistory
        )
    }


    fun openHistory() {

        _state.value = _state.value.copy(
            showHistory = true
        )
    }


    fun closeHistory() {

        _state.value = _state.value.copy(
            showHistory = false
        )
    }
    fun resendOtp() {

        val email = _state.value.email

        if (email.isBlank()) {
            setError("Email missing")
            return
        }

        val otp = OtpManager.generateOtp(email)

        AnalyticsLogger.logOtpGenerated(email, otp)

        _state.value = _state.value.copy(
            otp = "",
            error = "New OTP sent"
        )
    }



    private fun setError(msg: String) {

        _state.value = _state.value.copy(error = msg)
    }
}
