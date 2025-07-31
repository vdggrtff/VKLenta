package com.example.vklenta.data.network

import com.example.vklenta.data.model.NewsFeedResponseDto
import com.vk.id.AccessToken
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("newsfeed.getRecommended?v=5.199")
    suspend fun loadRecommendations(
        @Query("access_token") token: String
    ): NewsFeedResponseDto
}