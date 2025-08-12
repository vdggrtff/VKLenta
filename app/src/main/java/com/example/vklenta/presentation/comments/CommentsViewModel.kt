package com.example.vklenta.presentation.comments

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.vklenta.data.repository.NewsfeedRepositoryImpl
import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.usecase.GetCommentsUseCase
import kotlinx.coroutines.flow.map

class CommentsViewModel(application: Application, feedPost: FeedPost) : ViewModel() {

    private val repository = NewsfeedRepositoryImpl(application)

    private val getCommentsUseCase = GetCommentsUseCase(repository)

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
    
}