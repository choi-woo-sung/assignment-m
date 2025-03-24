package com.woosung.compose.data.model

import com.woosung.compose.domain.model.GoodsContainer
import kotlinx.serialization.Serializable

@Serializable
internal data class GoodsContainerResponse(
    val contents: ContentsResponse,
    val header: HeaderResponse? = null,
    val footer: FooterResponse? = null,
)


internal fun GoodsContainerResponse.toModel(): GoodsContainer {
    return GoodsContainer(
        contents = contents.toModel(),
        header = header?.toModel(),
        footer = footer?.toModel()
    )
}
