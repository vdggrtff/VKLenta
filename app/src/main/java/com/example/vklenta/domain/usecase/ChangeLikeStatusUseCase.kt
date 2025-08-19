package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.repository.NewsfeedRepository
import javax.inject.Inject

class ChangeLikeStatusUseCase @Inject constructor(
    private val repository: NewsfeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.changeLikeStatus(feedPost)
    }
}