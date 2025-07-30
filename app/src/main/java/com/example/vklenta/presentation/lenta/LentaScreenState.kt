package com.example.vklenta.presentation.lenta

import com.example.vklenta.domain.FeedPost

sealed class LentaScreenState {

    object Initial: LentaScreenState()
    data class Posts(val posts: List<FeedPost>): LentaScreenState()

}