package com.woosung.compose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    val linkURL: String,
    val thumbnailURL: String,
    val title: String,
    val description: String,
    val keyword: String,
)
