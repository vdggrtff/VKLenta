package com.example.vklenta.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vklenta.data.repository.NewsfeedRepositoryImpl
import com.example.vklenta.domain.usecase.CheckAuthStateUseCase
import com.example.vklenta.domain.usecase.GetAuthStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getAuthStateUseCase: GetAuthStateUseCase,
    private val checkAuthStateUseCase: CheckAuthStateUseCase,
) : ViewModel() {



    val authState = getAuthStateUseCase()

    fun checkAuthentification() {
        viewModelScope.launch {
            Log.d("MainActivity", "Есть Запуск")
            checkAuthStateUseCase()
        }
    }
}