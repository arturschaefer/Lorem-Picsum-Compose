package com.schaefer.lorempicsum.imagelist.data.datasource

import com.schaefer.lorempicsum.imagelist.data.model.ImageResponse

internal interface ImageListDataSource {

    suspend fun getImageList(): Result<List<ImageResponse>>
}
