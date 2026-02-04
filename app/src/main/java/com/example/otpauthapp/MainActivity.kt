package com.example.otpauthapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.otpauthapp.ui.*
import com.example.otpauthapp.viewmodel.AuthViewModel
import com.example.otpauthapp.viewmodel.AuthViewModelFactory
import com.example.otpauthapp.ui.theme.OtpAuthAppTheme
import timber.log.Timber

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.plant(Timber.DebugTree())

        setContent {

            OtpAuthAppTheme {

                val vm: AuthViewModel = viewModel(
                    factory = AuthViewModelFactory(this)
                )

                val state =
                    vm.state.collectAsStateWithLifecycle().value


                when {

                    state.showHistory ->
                        HistoryScreen(vm)

                    state.isLoggedIn ->
                        SessionScreen(vm)

                    state.isOtpSent ->
                        OtpScreen(vm)

                    else ->
                        LoginScreen(vm)
                }
            }
        }
    }
}
