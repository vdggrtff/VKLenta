package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.repository.NewsfeedRepository

class DeletePostUseCase(
    private val repository: NewsfeedRepository
) {

    suspend operator fun invoke(feedPost: FeedPost) {
        repository.deletePost(feedPost)
    }
}