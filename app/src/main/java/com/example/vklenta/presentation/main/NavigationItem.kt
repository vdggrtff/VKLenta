package com.example.vklenta.presentation.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vklenta.R
import com.example.vklenta.navigation.Screen

sealed class NavigationItem(
    val screen: Screen,
    val titleResId: Int,
    val icon: ImageVector
){
    object Home: NavigationItem(
        screen = Screen.Home,
        titleResId = R.string.bottom_item_home,
        icon = Icons.Outlined.Home
    )

    object Favorite: NavigationItem(
        screen = Screen.FavoriteFeed,
        titleResId = R.string.bottom_item_favorite,
        icon = Icons.Outlined.Favorite
    )

    object Profile: NavigationItem(
        screen = Screen.ProfileFeed,
        titleResId = R.string.bottom_item_profile,
        icon = Icons.Outlined.Person
    )
}