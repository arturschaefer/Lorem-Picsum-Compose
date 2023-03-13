package com.schaefer.lorempicsum.imagelist.presentation

import com.schaefer.lorempicsum.core.presentation.ViewModelAction

internal sealed class ImageListAction : ViewModelAction {
    object FetchImageList : ImageListAction()
}
