package com.example.vklenta.domain.usecase

import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.entity.PostComment
import com.example.vklenta.domain.repository.NewsfeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

class GetCommentsUseCase(
    private val repository: NewsfeedRepository,
) {

    operator fun invoke(feedPost: FeedPost): StateFlow<List<PostComment>> {
        return repository.getComments(feedPost)
    }
}