package com.woosung.compose.domain.model

data class Content(
    val type: ContentType,
    val banners: List<Banner>? = null,
    val goods: List<Good>? = null,
    val styles: List<Style>? = null,
)
