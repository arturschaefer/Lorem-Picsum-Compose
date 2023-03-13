package com.schaefer.lorempicsum.imagelist.data.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class ImageResponse(
    val id: String,
    @SerialName("download_url")
    val downloadUrl: String,
    val width: Long,
    val height: Long
)
