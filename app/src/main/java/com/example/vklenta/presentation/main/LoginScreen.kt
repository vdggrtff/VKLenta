package com.example.vklenta.presentation.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.vklenta.R
import com.vk.id.AccessToken
import com.vk.id.VKID
import com.vk.id.VKID.Companion.init
import com.vk.id.VKIDAuthFail
import com.vk.id.auth.VKIDAuthCallback
import com.vk.id.auth.VKIDAuthUiParams
import com.vk.id.onetap.common.OneTapOAuth
import com.vk.id.onetap.common.OneTapStyle
import com.vk.id.onetap.common.button.style.OneTapButtonCornersStyle
import com.vk.id.onetap.common.button.style.OneTapButtonElevationStyle
import com.vk.id.onetap.common.button.style.OneTapButtonSizeStyle
import com.vk.id.onetap.compose.onetap.OneTap
import com.vk.id.onetap.compose.onetap.OneTapTitleScenario

@Composable
fun LoginScreen(
    viewModel: MainViewModel,
) {

    val authParams = VKIDAuthUiParams {
        scopes = setOf("wall", "friends", "newsfeed", "email")
    }
    var token: AccessToken? by remember { mutableStateOf(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(200.dp),
            painter = painterResource(R.drawable.vk_logo),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(48.dp))
        Text(
            text = "VK Lenta",
            color = Color.Black,
            fontSize = 56.sp
        )
        Spacer(modifier = Modifier.height(48.dp))
        OneTap(
            modifier = Modifier.width(300.dp),
            authParams = authParams,
            onAuth = { oAuth, accessToken ->

                viewModel.onLoginSuccess(accessToken)
            },
            onFail = { _, _ ->
                viewModel.onLoginFailed()
            },
            scenario = OneTapTitleScenario.SignIn,
            signInAnotherAccountButtonEnabled = true,
            style = OneTapStyle.Dark(
                cornersStyle = OneTapButtonCornersStyle.Round,
                sizeStyle = OneTapButtonSizeStyle.MEDIUM_46,
                elevationStyle = OneTapButtonElevationStyle.Custom(8f)
            )
        )
    }
}
