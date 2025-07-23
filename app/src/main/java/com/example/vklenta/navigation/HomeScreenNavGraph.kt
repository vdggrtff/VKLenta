package com.example.vklenta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.vklenta.presentation.comments.domain.FeedPost

fun NavGraphBuilder.homeScreenNavGraph(
    lentaScreenContent: @Composable () -> Unit,
    commentsScreenContent: @Composable (FeedPost) -> Unit,
) {
    navigation(
        startDestination = Screen.NewsFeed.route,
        route = Screen.Home.route,
        builder = {
            composable(Screen.NewsFeed.route) {
                lentaScreenContent()
            }
            composable(
                route = Screen.Comments.route,
                arguments = listOf(
                    navArgument(Screen.KEY_FEED_POST){
                        type = FeedPost.NavigationType
                    }
                )
            ) {
                val feedPost = it.arguments?.getParcelable<FeedPost>(Screen.KEY_FEED_POST) ?: throw RuntimeException("Args is null")
                commentsScreenContent(feedPost)
            }
        }
    )
}