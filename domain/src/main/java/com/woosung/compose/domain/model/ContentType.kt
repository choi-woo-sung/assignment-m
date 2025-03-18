package com.woosung.compose.domain.model

enum class ContentType {
    BANNER,
    GRID,
    SCROLL,
    STYLE,
    UNKNOWN
}


sealed class Content {
    data class BannerType(val data: List<Banner>) : Content()
    data class GridType(val data: List<Good>) : Content()
    data class ScrollType(val data: List<Good>) : Content()
    data class StyleType(val data: List<Style>) : Content()
    object Unknown : Content()
}
