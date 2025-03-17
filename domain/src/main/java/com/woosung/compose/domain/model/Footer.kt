package com.woosung.compose.domain.model

data class Footer(
    val type: FooterType,
    val title: String,
    val iconURL: String? = null,
)
