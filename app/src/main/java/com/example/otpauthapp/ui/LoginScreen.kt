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
fun LoginScreen(vm: AuthViewModel) {

    val state by vm.state.collectAsState()

    var showOldData by remember { mutableStateOf(false) }



    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFF6A11CB),
            Color(0xFF2575FC)
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
                    "Welcome 👋",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Text("Login with Email")

                Spacer(Modifier.height(24.dp))



                OutlinedTextField(
                    value = state.email,
                    onValueChange = vm::setEmail,
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Email Address") },
                    singleLine = true
                )

                Spacer(Modifier.height(20.dp))



                Button(
                    onClick = vm::sendOtp,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Send OTP")
                }

                Spacer(Modifier.height(12.dp))



                OutlinedButton(
                    onClick = { showOldData = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("View Previous Sessions")
                }



                if (state.error.isNotEmpty()) {

                    Spacer(Modifier.height(8.dp))

                    Text(
                        state.error,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }
        }
    }



    if (showOldData) {

        AlertDialog(

            onDismissRequest = {
                showOldData = false
            },

            confirmButton = {

                TextButton(
                    onClick = { showOldData = false }
                ) {
                    Text("Close")
                }
            },

            title = {
                Text("Previous Sessions")
            },

            text = {

                Column {

                    if (state.history.isEmpty()) {

                        Text("No previous sessions found ❌")

                    } else {

                        val lastSession =
                            state.history.last()

                        val totalTime =
                            state.history.sum()

                        Text("Total Sessions: ${state.history.size}")

                        Spacer(Modifier.height(6.dp))

                        Text(
                            "Last Session: ${
                                formatTime(lastSession)
                            }"
                        )

                        Spacer(Modifier.height(6.dp))

                        Text(
                            "Total Time: ${
                                formatTime(totalTime)
                            }"
                        )
                    }
                }
            }
        )
    }
}
