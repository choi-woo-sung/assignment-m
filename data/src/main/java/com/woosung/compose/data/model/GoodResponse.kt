package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Good
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


fun GoodResponse.toModel(): Good {
    return Good(
        linkURL = linkURL,
        thumbnailURL = thumbnailURL,
        brandName = brandName,
        price = price,
        saleRate = saleRate,
        hasCoupon = hasCoupon,
    )
}
