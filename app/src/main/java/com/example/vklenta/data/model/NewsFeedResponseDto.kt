package com.example.vklenta.data.model

import com.google.gson.annotations.SerializedName

data class NewsFeedResponseDto(
    @SerializedName("response") val newsFeedContentDto: NewsFeedContentDto
)
