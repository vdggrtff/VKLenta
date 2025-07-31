package com.example.vklenta.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vk.id.AccessToken
import com.vk.id.VKID
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application = application) {

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    private var _currentToken: AccessToken? = null

    fun checkAuthentification() {
        viewModelScope.launch {
            _currentToken = VKID.instance.accessToken
            _authState.value =
                if (_currentToken != null) AuthState.Authorized else AuthState.NotAuthorized
        }
    }

    fun onLoginSuccess(token: AccessToken) {
        _currentToken = token
        _authState.value = AuthState.Authorized
    }

    fun onLoginFailed() {
        _authState.value = AuthState.NotAuthorized
    }

    fun logOut() {
        _authState.value = AuthState.NotAuthorized
    }

    companion object {
        const val PARAMS_WALL = "wall"
        const val PARAMS_GROUP = "group"
        const val PARAMS_FRIENDS = "friends"
    }

}