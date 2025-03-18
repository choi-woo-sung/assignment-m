package com.woosung.compose.data.model

import com.woosung.compose.domain.model.Product
import kotlinx.serialization.Serializable

//todo 네이밍 생각 해보기
@Serializable
data class ProductResponse(
    val contents: ContentsResponse,
    val header: HeaderResponse? = null,
    val footer: FooterResponse? = null,
)


fun ProductResponse.toModel(): Product {
    return Product(
        contents = contents.toModel(),
        header = header?.toModel(),
        footer = footer?.toModel()
    )
}
