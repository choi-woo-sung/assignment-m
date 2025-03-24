package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Header
import kotlinx.serialization.Serializable

@Serializable
internal data class HeaderResponse(
    val title: String,
    val iconURL: String? = null,
    val linkURL: String? = null,
)

internal fun HeaderResponse.toModel(): Header {
    return Header(
        title = title,
        iconURL = iconURL,
        linkURL = linkURL,
    )
}
