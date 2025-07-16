package com.example.vklenta.ui.theme

import androidx.activity.compose.BackHandler
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
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.navigation.Screen

@Composable
fun HomeScreen(
    viewModel: MainViewModel,
    innerPadding: PaddingValues
) {

    val screenState = viewModel.screenState.observeAsState(HomeScreenState.Initial)

    when (val currentState = screenState.value){
        is HomeScreenState.Posts -> {
            FeedPosts(
                viewModel = viewModel,
                innerPadding = innerPadding,
                posts = currentState.posts
            )
        }
        is HomeScreenState.Comments -> {
            CommentsScreen(
                feedPost = currentState.feedPost,
                comments = currentState.comments,
                onBackPressed = {
                    viewModel.closeComments()
                }
            )
            BackHandler {
                viewModel.closeComments()
            }
        }
        is HomeScreenState.Initial -> {
            
        }
    }
}

@Composable
fun FeedPosts(
    viewModel: MainViewModel,
    innerPadding: PaddingValues,
    posts: List<FeedPost>
){
    LazyColumn(
        modifier = Modifier.padding(innerPadding),
        contentPadding = PaddingValues(
            top = 16.dp,
            start = 8.dp,
            end = 8.dp,
        ),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(items = posts, key = { it.id }) { feedPost ->

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


                    /*onViewsClickListener = {statisticItem ->
                        viewModel.updateCount(feedPost = feedPost , item = statisticItem)
                    },*/
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
                    onCommentClickListener = {
                        viewModel.showComments(feedPost)
                    }
                )
            }
        }
    }
}