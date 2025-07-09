package com.example.vklenta.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.launch

@Composable
fun MainScreen() {
    val snackbarHostState = SnackbarHostState()
    val scope = rememberCoroutineScope()
    val fabIsVisible = remember {
        mutableStateOf(true)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        floatingActionButton = {
            if (fabIsVisible.value){
                FloatingActionButton(onClick = {
                    scope.launch {
                        val action = snackbarHostState.showSnackbar(
                            message = "This is snackbar",
                            actionLabel = "Hide FAB",
                            duration = SnackbarDuration.Long
                        )
                        if (action == SnackbarResult.ActionPerformed){
                            fabIsVisible.value = false
                        }
                    }
                }) {
                    Icon(imageVector = BottomBarItem.Favorite.icon, contentDescription = null)
                }
            }
        },
        bottomBar = {
            BottomAppBar() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Top
                ) {

                    val selectedItemIndex = remember { mutableIntStateOf(0) }

                    val items =
                        listOf(BottomBarItem.Home, BottomBarItem.Favorite, BottomBarItem.Profile)

                    items.forEachIndexed { index, item ->
                        val isSelected = selectedItemIndex.intValue == index
                        NavigationBarItem(
                            selected = selectedItemIndex.intValue == index,
                            icon = {
                                Icon(
                                    imageVector = item.icon, contentDescription = null,
                                    tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.titleResId),
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                                )
                            },
                            onClick = { selectedItemIndex.intValue = index },

                            )
                    }
                }
            }
        }
    ) {
        Text(
            text = "",
            modifier = Modifier.padding(it)
        )
    }
}