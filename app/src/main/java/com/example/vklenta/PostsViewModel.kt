package com.example.vklenta

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.domain.StatisticItem
import com.example.vklenta.ui.theme.LentaScreenState

class PostsViewModel : ViewModel() {

    private val sourceList = mutableListOf<FeedPost>().apply {
        repeat(10) {
            add(
                FeedPost(id = it)
            )
        }
    }
    private val initialState = LentaScreenState.Posts(posts = sourceList)
    private val _screenState = MutableLiveData<LentaScreenState>(initialState)
    val screenState: LiveData<LentaScreenState> = _screenState

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
    }

    fun deletePost(feedPost: FeedPost) {
        val currentState = screenState.value
        if (currentState !is LentaScreenState.Posts) return
        val oldPosts = currentState.posts.toMutableList()
        oldPosts.remove(feedPost)
        _screenState.value = LentaScreenState.Posts(posts = oldPosts)
    }
}