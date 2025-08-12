package com.example.vklenta.presentation.comments

import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.entity.PostComment

sealed class CommentsScreenState {

    object Initial: CommentsScreenState()
    data class Comments(
        val feedPost: FeedPost,
        val comments: List<PostComment>,
    ): CommentsScreenState()

}