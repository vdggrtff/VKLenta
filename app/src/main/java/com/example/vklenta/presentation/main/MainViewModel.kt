package com.example.vklenta.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.vklenta.data.repository.NewsfeedRepositoryImpl
import com.example.vklenta.domain.usecase.CheckAuthStateUseCase
import com.example.vklenta.domain.usecase.GetAuthStateUseCase
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application = application) {

    val repository = NewsfeedRepositoryImpl(application)

    private val getAuthStateUseCase = GetAuthStateUseCase(repository)
    private val checkAuthStateUseCase = CheckAuthStateUseCase(repository)

    val authState = getAuthStateUseCase()

     fun checkAuthentification() {
       viewModelScope.launch {
           checkAuthStateUseCase()
       }
    }
}