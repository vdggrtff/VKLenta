package com.example.vklenta.presentation.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKIDAuthFail
import com.vk.id.VKIDUser
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.auth.VKIDAuthParams
import com.vk.id.refresh.VKIDRefreshTokenCallback
import com.vk.id.refresh.VKIDRefreshTokenFail
import com.vk.id.refreshuser.VKIDGetUserCallback
import com.vk.id.refreshuser.VKIDGetUserFail
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application = application) {

    private val _authState = MutableLiveData<AuthState>(AuthState.Initial)
    val authState: LiveData<AuthState> = _authState

    private var currentToken: AccessToken? = null

    fun checkAuthentification() {
        viewModelScope.launch {
            currentToken = VKID.instance.accessToken
            _authState.value =
                if (currentToken != null) AuthState.Authorized else AuthState.NotAuthorized
            Log.d("MainViewModel", "${currentToken?.scopes}")
        }
    }

    fun onLoginSuccess(token: AccessToken) {
        //Log.d("MainViewModel", "${currentToken?.scopes}")
       /* viewModelScope.launch {

            VKID.instance.authorize(
                callback = object : VKIDAuthCallback {
                    override fun onAuth(accessToken: AccessToken) {
                        Log.d("MainViewModel", "${accessToken.scopes}")
                    }

                    override fun onFail(fail: VKIDAuthFail) {
                        TODO("Not yet implemented")
                    }

                },
                params = VKIDAuthParams {
                    scopes = setOf(PARAMS_WALL, PARAMS_FRIENDS)
                })
        }*/
        currentToken = token
        Log.d("MainViewModel", "${token.scopes}")
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