package com.schaefer.lorempicsum.imagelist.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
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
import com.schaefer.lorempicsum.core.presentation.Dimens
import com.schaefer.lorempicsum.imagelist.presentation.components.EmptyListContent
import com.schaefer.lorempicsum.imagelist.presentation.components.ErrorDialog
import com.schaefer.lorempicsum.imagelist.presentation.components.ImageCard

@OptIn(ExperimentalMaterialApi::class)
@Composable
internal fun ImageListContent(
    imageListViewModel: ImageListViewModel
) {
    var refreshing by remember { mutableStateOf(true) }

    val pullRefreshState: PullRefreshState = rememberPullRefreshState(
        refreshing = refreshing,
        onRefresh = imageListViewModel::getImageList
    )
    val state = imageListViewModel.state.collectAsState().value
    refreshing = state.isLoading

    if (state.hasError) {
        ErrorDialog(message = stringResource(id = R.string.problem_occurred_description)) {
            imageListViewModel.dialogButtonClicked()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {
        when (state.content) {
            is ImageListState.EmptyList -> {
                EmptyListContent()
            }

            is ImageListState.HasContent -> {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = IMAGE_MIN_SIZE),
                    contentPadding = PaddingValues(horizontal = Dimens.M, vertical = Dimens.S),
                    verticalArrangement = Arrangement.spacedBy(Dimens.S),
                    horizontalArrangement = Arrangement.spacedBy(Dimens.S),
                ) {
                    items(items = state.content.list, key = { it.id }) { item ->
                        ImageCard(urlImage = item.url)
                    }
                }
            }
        }

        PullRefreshIndicator(
            refreshing = refreshing,
            state = pullRefreshState,
            scale = true,
            modifier = Modifier.align(Alignment.TopCenter)
        )
    }
}

private val IMAGE_MIN_SIZE = 128.dp
