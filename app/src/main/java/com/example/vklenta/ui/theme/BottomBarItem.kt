package com.example.vklenta.ui.theme

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.vklenta.R

sealed class BottomBarItem(
    val titleResId: Int,
    val icon: ImageVector
){
    object Home: BottomBarItem(
        titleResId = R.string.bottom_item_home,
        icon = Icons.Outlined.Home
    )

    object Favorite: BottomBarItem(
        titleResId = R.string.bottom_item_favorite,
        icon = Icons.Outlined.Favorite
    )

    object Profile: BottomBarItem(
        titleResId = R.string.bottom_item_profile,
        icon = Icons.Outlined.Person
    )
}