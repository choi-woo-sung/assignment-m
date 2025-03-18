package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Banner
import kotlinx.serialization.Serializable

@Serializable
data class BannerResponse(
    val linkURL: String,
    val thumbnailURL: String,
    val title: String,
    val description: String,
    val keyword: String,
)

fun BannerResponse.toModel(): Banner {
    return Banner(
        linkURL = linkURL,
        thumbnailURL = thumbnailURL,
        title = title,
        description = description,
        keyword = keyword,
    )
}
