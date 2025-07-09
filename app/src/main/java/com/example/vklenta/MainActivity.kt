package com.example.vklenta

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vklenta.ui.theme.MainScreen
import com.example.vklenta.ui.theme.VKLentaTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VKLentaTheme {
                MainScreen()

        }
    }
}
}

/*
@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
private fun BottomAppBar() {
    Scaffold(
        bottomBar = {
            BottomAppBar() {
                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Top  ) {
                    Column {
                        IconButton(onClick = { */
/*TODO*//*
 }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                        Text(text = "Favorite")
                    }
                    Column {
                        IconButton(onClick = { */
/*TODO*//*
 }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                        Text(text = "Favorite")
                    }
                    Column {
                        IconButton(onClick = { */
/*TODO*//*
 }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                        Text(text = "Favorite")
                    }
                }
            }
        }
    ) {
        Text(
            modifier = Modifier.padding(it),
            text = "Scaffold Text"
        )
    }
}*/
