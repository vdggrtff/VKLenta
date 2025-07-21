package com.example.vklenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.vklenta.ui.theme.ActivityResultTest
import com.example.vklenta.ui.theme.MainScreen
import com.example.vklenta.ui.theme.VKLentaTheme

class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKLentaTheme {
              //  MainScreen()
                ActivityResultTest()
            }
        }
    }
}
