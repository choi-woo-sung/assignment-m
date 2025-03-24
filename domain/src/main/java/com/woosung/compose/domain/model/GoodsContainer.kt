package com.woosung.compose.domain.model

data class GoodsContainer(
    val contents: Content,
    val header: Header? = null,
    val footer: Footer? = null,
)
