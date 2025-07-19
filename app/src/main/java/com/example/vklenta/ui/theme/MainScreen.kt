package com.example.vklenta.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.vklenta.PostsViewModel
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.navigation.AppNavGraph
import com.example.vklenta.navigation.Screen
import com.example.vklenta.navigation.rememberNavigationState
import kotlin.collections.mutableListOf

@Composable
fun MainScreen() {
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

                    val items = listOf(
                        NavigationItem.Home,
                        NavigationItem.Favorite,
                        NavigationItem.Profile
                    )

                    items.forEach { item ->
                        val selected = navBackStackEntry?.destination?.hierarchy?.any {
                            it.route == item.screen.route
                        } ?: false

                        NavigationBarItem(
                            selected = selected,
                            icon = {
                                Icon(
                                    imageVector = item.icon, contentDescription = null,
                                    tint = contentColor(selected)
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.titleResId),
                                    color = contentColor(selected)
                                )
                            },
                            onClick = {
                                if (!selected) {
                                    navigationState.navigateTo(route = item.screen.route)
                                }
                            },
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        AppNavGraph(
            navHostController = navigationState.navHostController,
            lentaScreenContent = {
                HomeScreen(
                    innerPadding = innerPadding,
                    onCommentClickListener = {
                        navigationState.navigateToComments(it)
                    }
                )
            },
            commentsScreenContent = { feedPost ->
                CommentsScreen(
                    onBackPressed = {
                        navigationState.navHostController.popBackStack()
                    },
                    feedPost = feedPost
                )
            },
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

@Composable
private fun contentColor(isSelected: Boolean): Color {
    return if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
}

