package com.example.vklenta.presentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.vklenta.ui.theme.VKLentaTheme
import com.vk.id.VKID

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {

        VKID.init(this)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKLentaTheme {
                val viewModel: MainViewModel = viewModel()
                val authState = viewModel.authState.observeAsState(AuthState.Initial)

                when(authState.value){
                    is AuthState.Authorized -> {
                        MainScreen()
                    }
                    is AuthState.NotAuthorized -> {
                        LoginScreen(viewModel)
                    }
                    is AuthState.Initial -> {
                        viewModel.checkAuthentification()
                    }
                }

               /* val launcher = rememberLauncherForActivityResult(contract = VK.getVKAuthActivityResultContract()) {
                    viewModel.performAuthResult(it)
                }

                when(authState.value){
                    is AuthState.Authorized -> {
                        MainScreen()
                    }
                    is AuthState.NotAuthorized -> {
                        LoginScreen {
                            launcher.launch(listOf(VKScope.MESSAGES))
                        }
                    }
                    else -> {

                    }
                }*/

            }
        }
    }
}

/*@Composable
fun ScreenWithVKIDButton() {
    var userName by remember { mutableStateOf("") }
    OneTap(
        onAuth = { oneTapOAuth, token ->
            println("Успешная аутентификация: $oneTapOAuth")
            TODO()
        },
        signInAnotherAccountButtonEnabled = true,
    )
}


@Composable
fun VKIDAuthScreen(){
    val bottomSheetState = rememberOneTapBottomSheetState()
    OneTapBottomSheet(
        state = bottomSheetState,
        onAuth = { oAuth, token ->
            TODO()
        },
        onFail = { oAuth, fail ->
            when(fail){
                is VKIDAuthFail.Canceled -> TODO()
                is VKIDAuthFail.FailedApiCall -> TODO()
                is VKIDAuthFail.FailedOAuthState -> TODO()
                is VKIDAuthFail.FailedRedirectActivity -> TODO()
                is VKIDAuthFail.NoBrowserAvailable -> TODO()
                else -> TODO()
            }
        },
        serviceName = "VKLenta",
        scenario = OneTapScenario.EnterToAccount,
    )


}*/
