package com.woosung.compose.data.fake

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.model.ContentsResponse
import com.woosung.compose.data.model.FooterResponse
import com.woosung.compose.data.model.GoodsContainerResponse
import com.woosung.compose.data.model.HeaderResponse
import com.woosung.compose.data.model.StyleResponse
import com.woosung.compose.data.remote.api.GoodsRemoteSource

internal class SuccessStyleGoodsRemoteSource : GoodsRemoteSource {

    override suspend fun getGoodsList(): NetworkResult<List<GoodsContainerResponse>> {
        return NetworkResult.Success<List<GoodsContainerResponse>>(
            listOf(
                GoodsContainerResponse(
                    contents = ContentsResponse(
                        type = "STYLE",
                        banners = listOf(),
                        goods = listOf(),
                        styles = listOf<StyleResponse>(
                            StyleResponse(
                                linkURL = "1",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "2",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "3",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "4",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "5",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "6",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "7",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "8",
                                thumbnailURL = "테스트 섬네일",
                            ),
                            StyleResponse(
                                linkURL = "9",
                                thumbnailURL = "테스트 섬네일",
                            ),
                        ),
                    ),
                    header = HeaderResponse(
                        title = "테스트입니다.",
                    ),
                    footer = FooterResponse(
                        title = "Footer테스트",
                        type = "F",
                    ),
                )
            )
        )
    }
}
