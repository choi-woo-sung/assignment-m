package com.woosung.compose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class ContentsResponse(
    val type: String,
    val banners: List<BannerResponse>? = null,
    val goods: List<GoodResponse>? = null,
    val styles: List<StyleResponse>? = null,
)
