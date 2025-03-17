package com.woosung.compose.data.model
import kotlinx.serialization.Serializable


@Serializable
data class StyleResponse(
    val linkURL: String,
    val thumbnailURL: String,
)

