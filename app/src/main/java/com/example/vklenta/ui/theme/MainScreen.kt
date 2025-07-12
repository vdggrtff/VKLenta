package com.example.vklenta.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.vklenta.MainViewModel
import com.example.vklenta.domain.FeedPost
import com.example.vklenta.domain.StatisticItem
import com.example.vklenta.domain.StatisticType

@OptIn(ExperimentalFoundationApi::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun MainScreen(viewModel: MainViewModel) {
    Scaffold(
        bottomBar = {
            BottomAppBar() {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.Top
                ) {

                    val selectedItemIndex = remember { mutableIntStateOf(0) }

                    val items =
                        listOf(BottomBarItem.Home, BottomBarItem.Favorite, BottomBarItem.Profile)

                    items.forEachIndexed { index, item ->
                        val isSelected = selectedItemIndex.intValue == index
                        NavigationBarItem(
                            selected = selectedItemIndex.intValue == index,
                            icon = {
                                Icon(
                                    imageVector = item.icon, contentDescription = null,
                                    tint = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                                )
                            },
                            label = {
                                Text(
                                    text = stringResource(id = item.titleResId),
                                    color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary
                                )
                            },
                            onClick = { selectedItemIndex.intValue = index },
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        val feedPosts = viewModel.feedPosts.observeAsState(listOf())

        LazyColumn(
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
                    modifier = Modifier.animateItemPlacement(),
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {}
                ) {
                    ProfileCard(
                        feedPost = feedPost,


                        /*onViewsClickListener = {
                            viewModel.updateCount(it)
                        },*/
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
                        },
                    )
                }
            }
        }
    }
}