package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Style
import kotlinx.serialization.Serializable


@Serializable
internal data class StyleResponse(
    val linkURL: String,
    val thumbnailURL: String,
)

internal fun StyleResponse.toModel(): Style {
    return Style(
        linkURL = linkURL,
        thumbnailURL = thumbnailURL,
    )
}
