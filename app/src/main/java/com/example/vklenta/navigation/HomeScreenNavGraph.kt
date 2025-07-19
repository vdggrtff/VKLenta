package com.example.vklenta.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.ui.theme.FeedPosts

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
                    navArgument(Screen.KEY_FEED_POST_ID){
                        type = NavType.IntType
                    },
                    navArgument(Screen.KEY_FEED_POST_DESCRIPTION){
                        type = NavType.StringType
                    }
                )
            ) {
                //надо крч comments/{feed_post_id}
                val feedPostId = it.arguments?.getInt(Screen.KEY_FEED_POST_ID) ?: 0
                val feedPostDescription = it.arguments?.getString(Screen.KEY_FEED_POST_DESCRIPTION) ?: ""
                commentsScreenContent(FeedPost(id = feedPostId, contentText = feedPostDescription))
            }
        }
    )
}