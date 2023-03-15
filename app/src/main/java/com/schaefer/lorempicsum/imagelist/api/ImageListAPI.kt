package com.schaefer.lorempicsum.imagelist.api

import com.schaefer.lorempicsum.imagelist.data.model.ImageResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface ImageListAPI {

    @GET("v2/list/")
    suspend fun getImagesList(@Query("limit") limit: Long = 30): List<ImageResponse>
}
