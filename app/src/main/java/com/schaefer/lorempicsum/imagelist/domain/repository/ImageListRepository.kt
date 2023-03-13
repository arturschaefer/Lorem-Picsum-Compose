package com.schaefer.lorempicsum.imagelist.domain.repository

import com.schaefer.lorempicsum.imagelist.domain.model.ImageDomain

internal interface ImageListRepository {
    suspend fun getImageList(): Result<List<ImageDomain>>
}
