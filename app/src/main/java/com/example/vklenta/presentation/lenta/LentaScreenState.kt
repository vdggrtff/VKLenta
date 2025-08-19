package com.example.vklenta.presentation.lenta

import com.example.vklenta.domain.entity.FeedPost

sealed class LentaScreenState {

    object Initial: LentaScreenState()

    object Loading: LentaScreenState()

    data class Posts(
        val posts: List<FeedPost>,
        val nextDataIsLoading: Boolean = false,
    ): LentaScreenState()

}