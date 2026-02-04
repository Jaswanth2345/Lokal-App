package com.example.otpauthapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.otpauthapp.viewmodel.AuthViewModel

@Composable
fun OtpScreen(vm: AuthViewModel) {

    val state by vm.state.collectAsState()

    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFF11998E),
            Color(0xFF38EF7D)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradient),
        contentAlignment = Alignment.Center
    ) {

        Card(
            modifier = Modifier
                .padding(24.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            elevation = CardDefaults.cardElevation(12.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(
                    "Verify OTP 🔐",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text("Enter 6 digit code")

                Spacer(Modifier.height(24.dp))

                OutlinedTextField(
                    value = state.otp,
                    onValueChange = vm::setOtp,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("OTP") },
                    singleLine = true
                )

                Spacer(Modifier.height(20.dp))

                Button(
                    onClick = vm::verifyOtp,
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Verify")
                }

                TextButton(
                    onClick = vm::resendOtp
                ) {
                    Text("Resend OTP")
                }

                if (state.error.isNotEmpty()) {
                    Text(
                        state.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }
}
