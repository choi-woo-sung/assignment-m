package com.woosung.compose.domain.model

enum class ContentType {
    Banner, Grid, Scroll, Style, Unknown
}

sealed class Content(
    val type: ContentType,
    val endOfContent: Boolean = false,
) {

    data class BannerType(val data: List<Banner>) : Content(ContentType.Banner)

    data class GridType(
        val data: List<Goods>,
    ) : Content(ContentType.Grid)

    data class ScrollType(val data: List<Goods>) : Content(ContentType.Scroll)

    data class StyleType(val data: List<Style>) : Content(ContentType.Style)

    object Unknown : Content(ContentType.Unknown)
}
