package com.example.vklenta.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.vklenta.MainViewModel
import com.example.vklenta.domain.FeedPost

@Composable
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
        val feedPost = viewModel.feedPost.observeAsState(FeedPost())
        ProfileCard(
            modifier = Modifier.padding(innerPadding),
            feedPost = feedPost.value,
            /*onViewsClickListener = {
                viewModel.updateCount(it)
            },*/
            // или
            onViewsClickListener = viewModel::updateCount,
            onLikeClickListener = {
                viewModel.updateCount(it)
            },
            onShareClickListener = {
                viewModel.updateCount(it)
            },
            onCommentClickListener = {
                viewModel.updateCount(it)
            },
        )
    }
}