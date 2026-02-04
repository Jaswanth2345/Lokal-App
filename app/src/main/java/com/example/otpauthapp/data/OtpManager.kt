package com.example.otpauthapp.data

import kotlin.random.Random

data class OtpData(
    val otp: String,
    val time: Long,
    var attempts: Int
)

object OtpManager {

    private val otpMap = mutableMapOf<String, OtpData>()

    private const val EXPIRY = 60_000L
    private const val MAX_ATTEMPTS = 3

    fun generateOtp(email: String): String {
        val otp = Random.nextInt(100000, 999999).toString()

        otpMap[email] = OtpData(
            otp = otp,
            time = System.currentTimeMillis(),
            attempts = 0
        )

        return otp
    }

    fun validateOtp(email: String, input: String): String {

        val data = otpMap[email] ?: return "No OTP"

        if (System.currentTimeMillis() - data.time > EXPIRY) {
            otpMap.remove(email)
            return "Expired"
        }

        if (data.attempts >= MAX_ATTEMPTS) {
            return "Max Attempts"
        }

        if (data.otp == input) {
            otpMap.remove(email)
            return "Success"
        }

        data.attempts++
        return "Wrong"
    }
}
