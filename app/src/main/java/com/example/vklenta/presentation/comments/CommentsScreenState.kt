package com.example.vklenta.presentation.comments

import com.example.vklenta.domain.FeedPost
import com.example.vklenta.domain.PostComment

sealed class CommentsScreenState {

    object Initial: CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ): CommentsScreenState()

}