package com.example.otpauthapp.analytics

import timber.log.Timber

object AnalyticsLogger {

    fun logOtpGenerated(email: String, otp: String) {
        Timber.d("OTP for $email is $otp")
    }

    fun logOtpSuccess(email: String) {
        Timber.d("OTP Success for $email")
    }

    fun logOtpFailure(email: String) {
        Timber.d("OTP Failed for $email")
    }

    fun logLogout(email: String) {
        Timber.d("Logout: $email")
    }
}
