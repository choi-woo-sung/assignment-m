package com.woosung.compose.data.model

import kotlinx.serialization.Serializable

//todo 네이밍 생각 해보기
@Serializable
data class MainResponse(
    val contents: ContentsResponse,
    val header: HeaderResponse? = null,
    val footer: FooterResponse? = null,
)
