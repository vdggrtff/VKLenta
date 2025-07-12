package com.example.vklenta.domain

import com.example.vklenta.R

data class FeedPost (
    val communityName: String = "communityName",
    val publicationDate: String = "Time",
    val avatarResId: Int = R.drawable.post_comunity_thumbnail,
    val contentText: String = "Description",
    val contentImageResId: Int = R.drawable.post_content_image,
    val statistics: List<StatisticItem> = listOf(
        StatisticItem(type = StatisticType.VIEWS, count = 321),
        StatisticItem(type = StatisticType.SHARES, count = 9),
        StatisticItem(type = StatisticType.COMMENTS, count = 14),
        StatisticItem(type = StatisticType.LIKES, count = 54),
    )
)