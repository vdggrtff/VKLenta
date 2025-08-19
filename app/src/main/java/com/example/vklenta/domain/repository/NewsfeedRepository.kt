package com.example.vklenta.domain.repository

import android.app.Application
import com.example.vklenta.data.mapper.NewsFeedMapper
import com.example.vklenta.data.network.ApiFactory
import com.example.vklenta.domain.entity.FeedPost
import com.example.vklenta.domain.entity.PostComment
import com.example.vklenta.domain.entity.StatisticItem
import com.example.vklenta.domain.entity.StatisticType
import com.example.vklenta.extensions.mergeWith
import com.example.vklenta.domain.entity.AuthState
import com.vk.id.VKID
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retry
import kotlinx.coroutines.flow.stateIn
import java.lang.IllegalStateException

interface NewsfeedRepository {

    fun getAuthStateFlow(): StateFlow<AuthState>

    fun getRecommendations(): StateFlow<List<FeedPost>>

    fun getComments(feedPost: FeedPost): StateFlow<List<PostComment>>

    suspend fun loadNextData()

    suspend fun checkAuthState()

    suspend fun deletePost(feedPost: FeedPost)

    suspend fun changeLikeStatus(feedPost: FeedPost)
}