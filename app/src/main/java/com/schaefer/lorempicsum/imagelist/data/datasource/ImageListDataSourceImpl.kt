package com.schaefer.lorempicsum.imagelist.data.datasource

import com.schaefer.lorempicsum.imagelist.api.ImageListAPI
import com.schaefer.lorempicsum.imagelist.data.model.ImageResponse

internal class ImageListDataSourceImpl(
    private val imageListAPI: ImageListAPI
) : ImageListDataSource {

    override suspend fun getImageList(): Result<List<ImageResponse>> {
        return runCatching { imageListAPI.getImagesList() }
    }
}
