package com.schaefer.lorempicsum.imagelist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.schaefer.lorempicsum.R
import com.schaefer.lorempicsum.imagelist.presentation.components.ErrorDialog
import com.schaefer.lorempicsum.imagelist.presentation.components.ImageCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ImageListContent(imageListViewModel: ImageListViewModel) {
    var refreshing by remember { mutableStateOf(false) }

    val pullRefreshState: PullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = imageListViewModel::getImageList
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        when (val state = imageListViewModel.state.collectAsState().value) {
            ImageListState.EmptyList -> {
                refreshing = false
                Text(
                    text = stringResource(R.string.no_data_available),
                    modifier = Modifier.padding(16.dp)
                )
            }

            ImageListState.Error -> {
                refreshing = false
                ErrorDialog(message = stringResource(id = R.string.problem_occurred_description))
            }

            is ImageListState.HasContent -> {
                refreshing = false
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 128.dp),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    items(items = state.list, key = { it.id }) { item ->
                        ImageCard(urlImage = item.url)
                    }
                }
            }

            ImageListState.Loading -> {
                refreshing = true
            }
        }

        PullRefreshIndicator(refreshing = refreshing, state = pullRefreshState, scale = true)
    }
}
