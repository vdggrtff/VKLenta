package com.example.vklenta.domain

import android.os.Parcelable
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class FeedPost (
    val id: Long,
    val communityId: Long,
    val communityName: String,
    val publicationDate: String,
    val communityImageUrl: String,
    val contentText: String,
    val contentImageUrl: String?,
    val statistics: List<StatisticItem>,
    val isLiked: Boolean,
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