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

    init {
        /*val storage = VKPreferencesKeyValueStorage(application)
        val token = VKAccessToken.restore(storage)
        val loggedIn = token != null && token.isValid*/
       // _authState.value = if (loggedIn) AuthState.Authorized else AuthState.NotAuthorized
    }

    /*fun checkAuthentification() {
        val vkAuthCallback = object : VKIDAuthCallback {
            override fun onAuth(accessToken: AccessToken) {
                val token = accessToken.token
                _authState.value = AuthState.Authorized
                //...
            }

            override fun onFail(fail: VKIDAuthFail) {
                when (fail) {
                    is VKIDAuthFail.Canceled -> { *//*...*//*
                    }

                    else -> {
                        _authState.value = AuthState.NotAuthorized
                    }
                }
            }
        }
        viewModelScope.launch {
            VKID.instance.authorize(vkAuthCallback)
        }
    }*/

    fun checkAuthentification(){
        viewModelScope.launch {
            val token = VKID.instance.accessToken
            _authState.value = if (token != null) AuthState.Authorized else AuthState.NotAuthorized
        }
    }

    fun onLoginSuccess(token: AccessToken){
        _authState.value = AuthState.Authorized
    }

    fun onLoginFailed(){
        _authState.value = AuthState.NotAuthorized
    }

    fun logOut(){
        _authState.value = AuthState.NotAuthorized
    }

}