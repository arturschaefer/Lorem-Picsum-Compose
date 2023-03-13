package com.schaefer.lorempicsum.imagelist.data.mapper

import com.schaefer.lorempicsum.imagelist.data.model.ImageResponse
import com.schaefer.lorempicsum.imagelist.domain.model.ImageDomain

internal fun ImageResponse.toDomain(): ImageDomain {
    return ImageDomain(
        id = id,
        url = downloadUrl,
        width = width,
        height = height
    )
}
