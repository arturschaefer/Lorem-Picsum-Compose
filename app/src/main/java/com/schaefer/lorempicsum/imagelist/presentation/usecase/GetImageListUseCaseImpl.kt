package com.schaefer.lorempicsum.imagelist.presentation.usecase

import androidx.annotation.Keep
import com.schaefer.lorempicsum.imagelist.domain.repository.ImageListRepository
import com.schaefer.lorempicsum.imagelist.domain.usecase.GetImageListUseCase
import com.schaefer.lorempicsum.imagelist.presentation.mapper.toVO
import com.schaefer.lorempicsum.imagelist.presentation.model.ImageVO
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class GetImageListUseCaseImpl(
    private val repository: ImageListRepository,
) : GetImageListUseCase {

    override suspend fun observeList(): List<ImageVO> {
        return withContext(Dispatchers.IO) {
            repository.getImageList().map {
                it.map { imageDomain -> imageDomain.toVO() }
            }.onFailure {
                throw GetImageListException(it.message, it.cause)
            }.getOrThrow()
        }
    }
}

@Keep
internal class GetImageListException(
    override val message: String?,
    override val cause: Throwable?
) : Exception()
