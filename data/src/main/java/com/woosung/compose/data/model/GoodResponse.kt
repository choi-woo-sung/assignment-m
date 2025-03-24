package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Goods
import kotlinx.serialization.Serializable

@Serializable
internal data class GoodResponse(
    val linkURL: String,
    val thumbnailURL: String,
    val brandName: String,
    val price: Long,
    val saleRate: Long,
    val hasCoupon: Boolean,
)


internal fun GoodResponse.toModel(): Goods {
    return Goods(
        linkURL = linkURL,
        thumbnailURL = thumbnailURL,
        brandName = brandName,
        price = price,
        saleRate = saleRate,
        hasCoupon = hasCoupon,
    )
}
