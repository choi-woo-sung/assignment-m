package com.woosung.compose.data.model

import com.woosung.compose.domain.model.GoodsContainer
import kotlinx.serialization.Serializable

//todo 네이밍 생각 해보기
@Serializable
data class GoodsContainerResponse(
    val contents: ContentsResponse,
    val header: HeaderResponse? = null,
    val footer: FooterResponse? = null,
)


fun GoodsContainerResponse.toModel(): GoodsContainer {
    return GoodsContainer(
        contents = contents.toModel(),
        header = header?.toModel(),
        footer = footer?.toModel()
    )
}
