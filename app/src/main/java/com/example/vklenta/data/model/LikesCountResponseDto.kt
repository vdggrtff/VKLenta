package com.example.vklenta.data.model

import com.google.gson.annotations.SerializedName
import okhttp3.Response

data class LikesCountResponseDto(
    @SerializedName("response") val likes: LikeCountDto
)
