package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Content
import kotlinx.serialization.Serializable

@Serializable
data class ContentsResponse(
    val type: String,
    val banners: List<BannerResponse>? = null,
    val goods: List<GoodResponse>? = null,
    val styles: List<StyleResponse>? = null,
)

fun ContentsResponse.toModel(): Content {
    return when (type) {
        "BANNER" -> Content.BannerType(banners?.map { it.toModel() } ?: emptyList())
        "GRID" -> Content.GridType(goods?.map { it.toModel() } ?: emptyList())
        "SCROLL" -> Content.ScrollType(goods?.map { it.toModel() } ?: emptyList())
        "STYLE" -> Content.StyleType(styles?.map { it.toModel() } ?: emptyList())
        else -> Content.Unknown
    }
}
