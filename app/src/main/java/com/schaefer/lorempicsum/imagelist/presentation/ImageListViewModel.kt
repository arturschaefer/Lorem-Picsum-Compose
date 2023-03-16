package com.schaefer.lorempicsum.imagelist.presentation

import androidx.lifecycle.viewModelScope
import com.schaefer.lorempicsum.core.presentation.viewmodel.ViewModelActionState
import com.schaefer.lorempicsum.imagelist.domain.usecase.GetImageListUseCase
import com.schaefer.lorempicsum.imagelist.presentation.model.ImageVO
import kotlinx.coroutines.launch
import timber.log.Timber

internal class ImageListViewModel(
    private val getImageListUseCase: GetImageListUseCase
) : ViewModelActionState<ImageListFullState, ImageListAction>(
    ImageListFullState(content = ImageListState.EmptyList)
) {

    init {
        getImageList()
    }

    fun getImageList() {
        setState(state.value.copy(isLoading = true, hasError = false))
        viewModelScope.launch {
            runCatching { getImageListUseCase.observeList() }
                .onFailure { handleError(it) }
                .onSuccess { handleSuccess(it) }
        }
    }

    fun dialogButtonClicked() {
        setState(state.value.copy(hasError = false))
    }

    private fun handleError(it: Throwable) {
        Timber.e(it)
        setState(
            state.value.copy(
                isLoading = false,
                hasError = true
            )
        )
    }

    private fun handleSuccess(list: List<ImageVO>) {
        setState(
            state.value.copy(
                isLoading = false,
                hasError = false,
                content = ImageListState.HasContent(list)
            )
        )
    }
}
