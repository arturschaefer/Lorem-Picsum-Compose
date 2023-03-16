package com.schaefer.lorempicsum.imagelist.presentation

import com.schaefer.lorempicsum.core.presentation.ViewModelState
import com.schaefer.lorempicsum.imagelist.presentation.model.ImageVO
internal data class ImageListFullState(
    val isLoading: Boolean = false,
    val hasError: Boolean = false,
    val content: ImageListState,
) : ViewModelState

internal sealed class ImageListState : ViewModelState {

    object EmptyList : ImageListState()

    data class HasContent(val list: List<ImageVO>) : ImageListState()
}
