package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Style
import kotlinx.serialization.Serializable


@Serializable
data class StyleResponse(
    val linkURL: String,
    val thumbnailURL: String,
)

fun StyleResponse.toModel(): Style {
    return Style(
        linkURL = linkURL,
        thumbnailURL = thumbnailURL,
    )
}
