package com.woosung.compose.data.model

import kotlinx.serialization.Serializable

@Serializable
data class GoodResponse(
    val linkURL: String,
    val thumbnailURL: String,
    val brandName: String,
    val price: Long,
    val saleRate: Long,
    val hasCoupon: Boolean,
)
