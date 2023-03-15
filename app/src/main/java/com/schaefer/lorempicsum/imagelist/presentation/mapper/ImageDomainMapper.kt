package com.schaefer.lorempicsum.imagelist.presentation.mapper

import com.schaefer.lorempicsum.imagelist.domain.model.ImageDomain
import com.schaefer.lorempicsum.imagelist.presentation.model.ImageVO

internal fun ImageDomain.toVO(): ImageVO {
    return ImageVO(
        id = id,
        url = url,
        width = width,
        height = height
    )
}
