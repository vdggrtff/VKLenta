package com.example.vklenta.presentation.lenta

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.vklenta.data.repository.NewsfeedRepository
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.domain.StatisticItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class PostsViewModel(application: Application) : AndroidViewModel(application) {

    private val initialState = LentaScreenState.Initial
    private val _screenState = MutableLiveData<LentaScreenState>(initialState)
    val screenState: LiveData<LentaScreenState> = _screenState

    private val repository = NewsfeedRepository(application)

    init {
        _screenState.value = LentaScreenState.Loading
        loadRecommendations()
    }

    private fun loadRecommendations() {
        viewModelScope.launch {
            val feedPosts = repository.loadRecommendations()
            _screenState.value = LentaScreenState.Posts(posts = feedPosts)
        }
    }

    fun loadNextRecommendations(){
        _screenState.value = LentaScreenState.Posts(
            posts = repository.feedPosts,
            nextDataIsLoading = true
        )
       loadRecommendations()
    }

    fun deletePost(feedPost: FeedPost){
        viewModelScope.launch {
            repository.deletePost(feedPost)
            _screenState.value = LentaScreenState.Posts(posts = repository.feedPosts)
        }
    }

    fun changeLikeStatus(feedPost: FeedPost) {
        viewModelScope.launch {
            repository.changeLikeStatus(feedPost)
            _screenState.value = LentaScreenState.Posts(posts = repository.feedPosts)
        }
    }
}