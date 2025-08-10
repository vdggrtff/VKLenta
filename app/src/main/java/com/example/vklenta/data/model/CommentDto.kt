package com.example.vklenta.data.model

import com.google.gson.annotations.SerializedName

data class CommentDto (
    @SerializedName("id") val id: Long,
    @SerializedName("from_id") val authorId: Long,
    @SerializedName("text") val text: String,
    @SerializedName("data") val data: Long,
)