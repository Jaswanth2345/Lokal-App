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
import kotlinx.coroutines.delay

@Composable
fun SessionScreen(vm: AuthViewModel) {

    val state by vm.state.collectAsState()


    var time by remember { mutableStateOf(0L) }



    LaunchedEffect(state.sessionStart) {

        while (true) {

            delay(1000)

            time = (System.currentTimeMillis() -
                    state.sessionStart) / 1000
        }
    }


    val min = time / 60
    val sec = time % 60



    val gradient = Brush.verticalGradient(
        listOf(
            Color(0xFF141E30),
            Color(0xFF243B55)
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
            elevation = CardDefaults.cardElevation(16.dp)
        ) {

            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Text(
                    "Your Session ⏱",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Bold
                )

                Spacer(Modifier.height(24.dp))



                Text(
                    "%02d:%02d".format(min, sec),
                    style = MaterialTheme.typography.displayLarge,
                    color = MaterialTheme.colorScheme.primary
                )

                Spacer(Modifier.height(32.dp))


                Button(
                    onClick = vm::openHistory,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("View History")
                }

                Spacer(Modifier.height(12.dp))


                OutlinedButton(
                    onClick = vm::logout,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Logout")
                }
            }
        }
    }
}
