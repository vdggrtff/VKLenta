package com.example.vklenta.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.vklenta.MainViewModel
import com.example.vklenta.navigation.AppNavGraph
import com.example.vklenta.navigation.NavigationState
import com.example.vklenta.navigation.Screen
import com.example.vklenta.navigation.rememberNavigationState

@OptIn(ExperimentalFoundationApi::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MainScreen(viewModel: MainViewModel) {
    val navigationState = rememberNavigationState()

    Scaffold(
        bottomBar = {
            BottomAppBar() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Top
                ) {

                    val navBackStackEntry by navigationState.navHostController.currentBackStackEntryAsState()
                    val currentRout = navBackStackEntry?.destination?.route

                    val items =
                        listOf(NavigationItem.Home, NavigationItem.Favorite, NavigationItem.Profile)

                    items.forEach { item ->
                        val isSelected = currentRout == item.screen.route
                        val contentColor =
                            if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                        NavigationBarItem(
                            selected = currentRout == item.screen.route,
                            icon = {
                                Icon(
                                    imageVector = item.icon, contentDescription = null,
                                    tint = contentColor
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.titleResId),
                                    color = contentColor
                                )
                            },
                            onClick = { navigationState.navigateTo(route = item.screen.route) },
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            homeScreenContent = { HomeScreen(viewModel, innerPadding) },
            favoriteScreenContent = { TextCounter("Favorite") },
            profileScreenContent = { TextCounter("Profile") },
        )

    }
}

@Composable
private fun TextCounter(name: String) {
    var count by rememberSaveable {
        mutableStateOf(0)
    }

    Text(
        modifier = Modifier
            .padding(16.dp)
            .clickable { count++ },
        text = "$name Count: $count",
        color = Color.Black
    )
}

