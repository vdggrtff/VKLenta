package com.example.vklenta.presentation.lenta

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vklenta.data.mapper.NewsFeedMapper
import com.example.vklenta.data.model.NewsFeedContentDto
import com.example.vklenta.data.network.ApiFactory
import com.example.vklenta.data.network.ApiService
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.domain.StatisticItem
import com.vk.id.VKID
import kotlinx.coroutines.launch

class PostsViewModel : ViewModel() {

    private val initialState = LentaScreenState.Initial
    private val _screenState = MutableLiveData<LentaScreenState>(initialState)
    val screenState: LiveData<LentaScreenState> = _screenState

    private val mapper = NewsFeedMapper()

    init {
        loadRecommendations()
    }

    private fun loadRecommendations(){
        viewModelScope.launch {
            val token = VKID.instance.accessToken?.token ?: run {
                Log.d("loadRecommendations", "Нужен Вход")
                return@launch
            }
            val response = ApiFactory.apiService.loadRecommendations(token)
            val feedPosts = mapper.mapResponseToPosts(response)
            _screenState.value = LentaScreenState.Posts(posts = feedPosts)
        }
    }

    fun updateCount(feedPost: FeedPost, item: StatisticItem) {
        val currentState = screenState.value
        if (currentState !is LentaScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        val oldStatistics = feedPost.statistics
        val newStatistics = oldStatistics.toMutableList().apply {
            replaceAll() { oldItem ->
                if (oldItem.type == item.type) {
                    oldItem.copy(count = oldItem.count + 1)
                } else {
                    oldItem
                }
            }
        }
        val newFeedPost = feedPost.copy(statistics = newStatistics)
        val newPosts = oldPosts.apply {
            replaceAll {
                if (it.id == newFeedPost.id) {
                    newFeedPost
                } else {
                    it
                }
            }
        }
        _screenState.value = LentaScreenState.Posts(posts = newPosts)
        Log.d("dismissState", "Updating count for post with ID: ${feedPost.id}")
    }

    fun deletePost(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is LentaScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = LentaScreenState.Posts(posts = oldPosts)
    }
}