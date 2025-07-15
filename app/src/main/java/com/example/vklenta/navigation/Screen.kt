package com.example.vklenta.navigation

sealed class Screen (
    val route: String
){

    object NewsFeed: Screen(ROUTE_NEWS_FEED)

    object FavoriteFeed: Screen(ROUTE_FAVORITE_FEED)

    object ProfileFeed: Screen(ROUTE_PROFILE_FEED)

    private companion object{

        const val ROUTE_NEWS_FEED = "news_feed"
        const val ROUTE_FAVORITE_FEED = "news_favorite"
        const val ROUTE_PROFILE_FEED = "news_profile"
    }
}