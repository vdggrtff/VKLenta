package com.example.vklenta.data.model

import com.google.gson.annotations.SerializedName

data class LikeCountDto (
    @SerializedName("likes") val count: Int
)