package com.schaefer.lorempicsum.imagelist.presentation

import androidx.lifecycle.viewModelScope
import com.schaefer.lorempicsum.core.presentation.viewmodel.ViewModelActionState
import com.schaefer.lorempicsum.imagelist.domain.usecase.GetImageListUseCase
import com.schaefer.lorempicsum.imagelist.presentation.model.ImageVO
import kotlinx.coroutines.launch
import timber.log.Timber

internal class ImageListViewModel(
    private val getImageListUseCase: GetImageListUseCase
) : ViewModelActionState<ImageListState, ImageListAction>(ImageListState.Loading) {

    init {
        getImageList()
    }

    fun getImageList() {
        setState(ImageListState.Loading)
        viewModelScope.launch {
            runCatching { getImageListUseCase.observeList() }
                .onFailure { handleError(it) }
                .onSuccess { handleSuccess(it) }
        }
    }

    private fun handleError(it: Throwable) {
        Timber.e(it)
        setState(ImageListState.Error)
    }

    private fun handleSuccess(it: List<ImageVO>) {
        setState(ImageListState.HasContent(it))
    }
}
