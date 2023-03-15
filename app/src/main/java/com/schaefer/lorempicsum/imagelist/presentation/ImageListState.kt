package com.schaefer.lorempicsum.imagelist.presentation

import com.schaefer.lorempicsum.core.presentation.ViewModelState
import com.schaefer.lorempicsum.imagelist.presentation.model.ImageVO

internal sealed class ImageListState : ViewModelState {
    object Loading : ImageListState()

    object EmptyList : ImageListState()

    object Error : ImageListState()

    data class HasContent(val list: List<ImageVO>) : ImageListState()
}
