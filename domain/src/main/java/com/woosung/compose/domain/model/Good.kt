package com.woosung.compose.domain.model

data class Good(
    val linkURL: String,
    val thumbnailURL: String,
    val brandName: String,
    val price: Long,
    val saleRate: Long,
    val hasCoupon: Boolean,
)

