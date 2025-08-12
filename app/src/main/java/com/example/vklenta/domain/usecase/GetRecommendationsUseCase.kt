package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.repository.NewsfeedRepository
import kotlinx.coroutines.flow.StateFlow

class GetRecommendationsUseCase(
    private val repository: NewsfeedRepository
) {

    operator fun invoke(): StateFlow<List<FeedPost>>{
        return repository.getRecommendations()
    }
}