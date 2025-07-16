package com.example.vklenta.ui.theme

import com.example.vklenta.domain.FeedPost
import com.example.vklenta.domain.PostComment

sealed class HomeScreenState {

    object Initial: HomeScreenState()
    data class Posts(val posts: List<FeedPost>): HomeScreenState()

    data class Comments(val feedPost: FeedPost, val comments: List<PostComment>): HomeScreenState()
}