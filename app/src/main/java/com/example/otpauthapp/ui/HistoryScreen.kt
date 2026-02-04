package com.example.otpauthapp.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.otpauthapp.viewmodel.AuthViewModel

@Composable
fun HistoryScreen(vm: AuthViewModel) {

    val state by vm.state.collectAsState()


    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            "Session History 📜",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(Modifier.height(16.dp))


        if (state.history.isEmpty()) {

            Text("No sessions yet")

        } else {

            LazyColumn {

                itemsIndexed(state.history) { i, sec ->

                    Card(
                        Modifier
                            .fillMaxWidth()
                            .padding(6.dp)
                    ) {

                        Column(Modifier.padding(12.dp)) {

                            Text("Session ${i + 1}")

                            Text(
                                "Duration: ${formatTime(sec)}"
                            )
                        }
                    }
                }
            }
        }

        Spacer(Modifier.height(16.dp))


        Button(
            onClick = vm::closeHistory,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Back")
        }
    }
}
