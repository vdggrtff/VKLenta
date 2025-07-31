package com.example.vklenta.data.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class AttachmentDto(
    @SerializedName("photo") val photo: PhotoDto?
)
