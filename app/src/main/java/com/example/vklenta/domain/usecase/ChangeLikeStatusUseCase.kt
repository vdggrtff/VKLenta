package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.repository.NewsfeedRepository

class ChangeLikeStatusUseCase(
    private val repository: NewsfeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}