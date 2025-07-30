package com.example.vklenta.domain

import com.example.vklenta.R

data class PostComment (
    val id: Int,
    val authorName: String = "Author",
    val authorResId: Int = R.drawable.comment_author_avatar,
    val comment: String = "Long comment Text",
    val publicationDate: String = "14:00"
)