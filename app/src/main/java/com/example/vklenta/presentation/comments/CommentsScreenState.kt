package com.example.vklenta.presentation.comments

import com.example.vklenta.presentation.comments.domain.FeedPost
import com.example.vklenta.presentation.comments.domain.PostComment

sealed class CommentsScreenState {

    object Initial: CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ): CommentsScreenState()

}