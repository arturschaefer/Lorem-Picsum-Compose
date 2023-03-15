package com.schaefer.lorempicsum.imagelist.data.repository

import com.schaefer.lorempicsum.imagelist.data.datasource.ImageListDataSource
import com.schaefer.lorempicsum.imagelist.data.mapper.toDomain
import com.schaefer.lorempicsum.imagelist.domain.model.ImageDomain
import com.schaefer.lorempicsum.imagelist.domain.repository.ImageListRepository

internal class ImageListRepositoryImpl(
    private val remoteDataSource: ImageListDataSource
) : ImageListRepository {

    override suspend fun getImageList(): Result<List<ImageDomain>> {
        return remoteDataSource.getImageList().map { list -> list.map { it.toDomain() } }
    }
}
