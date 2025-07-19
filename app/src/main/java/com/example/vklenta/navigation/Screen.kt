package com.example.vklenta.navigation

import android.net.Uri
import com.example.vklenta.domain.FeedPost

sealed class Screen (
    val route: String
){

    object NewsFeed: Screen(ROUTE_NEWS_FEED)

    object FavoriteFeed: Screen(ROUTE_FAVORITE_FEED)

    object ProfileFeed: Screen(ROUTE_PROFILE_FEED)

    object Home: Screen(ROUTE_HOME)

    object Comments: Screen(ROUTE_COMMENTS){

        private const val ROUTE_FOR_ARGS = "comments"

        fun getRouteWithArgs(feedPost: FeedPost): String{
            return "$ROUTE_FOR_ARGS/${feedPost.id}/${feedPost.contentText.encode()}" //крч для спец Символов исп Uri.encode(...)
        }
    }

    companion object{

        const val KEY_FEED_POST_DESCRIPTION = "feed_post_description"
        const val KEY_FEED_POST_ID = "feed_post_id"
        const val ROUTE_HOME = "home"
        const val ROUTE_COMMENTS = "comments/{$KEY_FEED_POST_ID}/{$KEY_FEED_POST_DESCRIPTION}"
        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVORITE_FEED = "news_favorite"
        const val ROUTE_PROFILE_FEED = "news_profile"
    }
}

fun String.encode(): String{
    return Uri.encode(this)
}