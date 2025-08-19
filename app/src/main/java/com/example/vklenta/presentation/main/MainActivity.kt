package com.example.vklenta.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vklenta.domain.entity.AuthState
import com.example.vklenta.ui.theme.VKLentaTheme
import com.vk.id.VKID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        VKID.init(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKLentaTheme {

                Log.d("MainActivity", "Есть Запуск")

                val viewModel: MainViewModel = hiltViewModel<MainViewModel>()
                val authState = viewModel.authState.collectAsState(AuthState.Initial)

                Log.d("MainActivity", "Есть Запуск")

                when(authState.value){
                    is AuthState.Authorized -> {
                        MainScreen()
                        Log.d("MainActivity", "Есть Запуск")
                    }
                    is AuthState.NotAuthorized -> {
                        LoginScreen(viewModel)
                    }
                    is AuthState.Initial -> {
                        viewModel.checkAuthentification()
                    }
                }
            }
        }
    }

    companion object
}
