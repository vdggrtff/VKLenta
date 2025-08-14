package com.example.vklenta.presentation.comments

import android.app.Application
import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.vklenta.data.repository.NewsfeedRepositoryImpl
import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.usecase.GetCommentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel
class CommentsViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val getCommentsUseCase: GetCommentsUseCase
) : ViewModel() {


    private val feedPost: FeedPost by lazy {
        Log.d("MainActivity", "Есть Запуск")
        savedStateHandle.get<FeedPost>("feedPost") ?: throw IllegalStateException("FeedPost required")
    }

    val screenState = getCommentsUseCase(feedPost)
        .map {
            CommentsScreenState.Comments(
                feedPost = feedPost,
                comments = it
            )
        }
    
}