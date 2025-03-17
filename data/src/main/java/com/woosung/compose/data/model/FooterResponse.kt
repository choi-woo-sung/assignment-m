package com.woosung.compose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class FooterResponse(
    val type: String,
    val title: String,
    val iconURL: String? = null,
)
