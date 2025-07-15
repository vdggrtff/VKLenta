package com.example.vklenta.ui.theme

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vklenta.MainViewModel

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    innerPadding: PaddingValues
) {

    val feedPosts = viewModel.feedPosts.observeAsState(listOf())

    if (feedPosts.value.isNotEmpty()){
        CommentsScreen(feedPosts.value.get(0))
    }


    /*LazyColumn(
        modifier = Modifier.padding(innerPadding),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = feedPosts.value, key = { it.id }) { feedPost ->

            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = {
                    when (it) {
                        SwipeToDismissBoxValue.EndToStart -> {
                            viewModel.deletePost(feedPost = feedPost)
                            true
                        }
                        else -> {
                            false
                        }
                    }
                }
            )

            SwipeToDismissBox(
                modifier = Modifier.animateItem(),
                state = dismissState,
                enableDismissFromStartToEnd = false,
                backgroundContent = {}
            ) {
                ProfileCard(
                    feedPost = feedPost,


                    *//*onViewsClickListener = {
                        viewModel.updateCount(it)
                    },*//*
                    // или
                    onViewsClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    },
                    onLikeClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    },
                    onShareClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    },
                    onCommentClickListener = { statisticItem ->
                        viewModel.updateCount(
                            feedPost = feedPost,
                            item = statisticItem
                        )
                    }
                )
            }
        }
    }*/
}