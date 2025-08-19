package com.example.vklenta.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class PhotoUrlDto(
    @SerializedName("url") val url: String
)
