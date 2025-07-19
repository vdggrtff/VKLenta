package com.example.vklenta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.vklenta.domain.FeedPost

@Composable
fun AppNavGraph(
    navHostController: NavHostController,
    lentaScreenContent: @Composable () -> Unit,
    favoriteScreenContent: @Composable () -> Unit,
    profileScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
){
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route
    ){
        homeScreenNavGraph(
            lentaScreenContent = lentaScreenContent,
            commentsScreenContent = commentsScreenContent
        )
        composable(Screen.FavoriteFeed.route) {
            favoriteScreenContent()
        }
        composable(Screen.ProfileFeed.route) {
            profileScreenContent()
        }
    }
}