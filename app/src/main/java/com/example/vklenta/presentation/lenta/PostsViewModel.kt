package com.example.vklenta.presentation.lenta

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vklenta.data.repository.NewsfeedRepositoryImpl
import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.usecase.ChangeLikeStatusUseCase
import com.example.vklenta.domain.usecase.DeletePostUseCase
import com.example.vklenta.domain.usecase.GetRecommendationsUseCase
import com.example.vklenta.domain.usecase.LoadNextDataUseCase
import com.example.vklenta.extensions.mergeWith
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getRecommendationsUseCase: GetRecommendationsUseCase,
    private val loadNextDataUseCase: LoadNextDataUseCase,
    private val changeLikeStatusUseCase: ChangeLikeStatusUseCase,
    private val deletePosUseCase: DeletePostUseCase,
) : ViewModel() {

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        Log.d("PostsViewModel", "Exception caught by exception handler")
    }

    private val recommendationsFlow = getRecommendationsUseCase()
    private val loadNextDataFlow = MutableSharedFlow<LentaScreenState>()

    val screenState = recommendationsFlow
        .filter { it.isNotEmpty() }
        .map { LentaScreenState.Posts(posts = it) as LentaScreenState }
        .onStart { emit(LentaScreenState.Loading) }
        .mergeWith(loadNextDataFlow)


    fun loadNextRecommendations() {
        viewModelScope.launch {
            Log.d("MainActivity", "Есть Запуск")
            loadNextDataFlow.emit(
                LentaScreenState.Posts(
                    posts = recommendationsFlow.value,
                    nextDataIsLoading = true,
                )
            )
            loadNextDataUseCase()
        }
    }

    fun deletePost(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            deletePosUseCase(feedPost)
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch(exceptionHandler) {
            changeLikeStatusUseCase(feedPost)
        }
    }
}