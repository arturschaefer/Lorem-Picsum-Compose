package com.schaefer.lorempicsum.imagelist.presentation.model

import android.os.Parcelable
import androidx.annotation.Keep
import kotlinx.parcelize.Parcelize

@Keep
@Parcelize
data class ImageVO(
    val id: String,
    val url: String,
    val width: Long,
    val height: Long
) : Parcelable
