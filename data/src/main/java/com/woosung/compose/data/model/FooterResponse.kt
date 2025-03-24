package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Footer
import com.woosung.compose.domain.model.FooterType
import kotlinx.serialization.Serializable

@Serializable
data class FooterResponse(
    val type: String,
    val title: String,
    val iconURL: String? = null,
)


fun FooterResponse.toModel(): Footer {
    return Footer(
        type = runCatching { FooterType.valueOf(this.type) }.getOrElse { FooterType.UNKNOWN },
        title = title,
        iconURL = iconURL,
    )
}
