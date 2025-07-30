package com.example.vklenta.data.mapper

import com.example.vklenta.data.model.NewsFeedResponseDto
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.domain.StatisticItem
import com.example.vklenta.domain.StatisticType
import kotlin.math.absoluteValue

class NewsFeedMapper {

    fun mapResponseToPosts(responseDto: NewsFeedResponseDto): List<FeedPost>{
        val result = mutableListOf<FeedPost>()
        val posts = responseDto.newsFeedContentDto.posts
        val groups = responseDto.newsFeedContentDto.groups

        for(post in posts){
            val group = groups.find {
                it.id == post.communityId.absoluteValue
            } ?: break
            val feedPost = FeedPost(
                id = post.id,
                communityName = group.name,
                publicationDate = post.date.toString(),
                communityImageUrl = group.imageUrl,
                contentText = post.text,
                contentImageUrl = post.attachments?.firstOrNull()?.photo?.photoUrls?.lastOrNull()?.url,
                statistics = listOf(
                    StatisticItem(type = StatisticType.LIKES, post.likes.count),
                    StatisticItem(type = StatisticType.VIEWS, post.views.count),
                    StatisticItem(type = StatisticType.COMMENTS, post.comments.count),
                    StatisticItem(type = StatisticType.SHARES, post.reposts.count),
                )
            )
            result.add(feedPost)
        }
        return result
    }
}