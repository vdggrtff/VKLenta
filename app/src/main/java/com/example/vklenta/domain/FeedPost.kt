package com.example.vklenta.domain

import android.os.Parcelable
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.vklenta.R
import com.example.vklenta.ui.theme.FeedPosts
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost (
    val id: Int = 0,
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
): Parcelable{

    companion object{
        val NavigationType: NavType<FeedPost> = object : NavType<FeedPost>(false){
            override fun get(
                bundle: SavedState,
                key: String,
            ): FeedPost? {
                return bundle.getParcelable(key)
            }

            override fun parseValue(value: String): FeedPost {
                return Gson().fromJson(value, FeedPost::class.java)
            }

            override fun put(
                bundle: SavedState,
                key: String,
                value: FeedPost,
            ) {
               bundle.putParcelable(key, value)
            }
        }
    }
}