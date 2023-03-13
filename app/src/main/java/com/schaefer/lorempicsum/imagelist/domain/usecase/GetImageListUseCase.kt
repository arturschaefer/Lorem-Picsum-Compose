package com.schaefer.lorempicsum.imagelist.domain.usecase

import com.schaefer.lorempicsum.imagelist.presentation.model.ImageVO

internal interface GetImageListUseCase {
    suspend fun observeList(): List<ImageVO>
}
