package com.woosung.compose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HeaderResponse(
    val title: String,
    val iconURL: String? = null,
    val linkURL: String? = null,
)
