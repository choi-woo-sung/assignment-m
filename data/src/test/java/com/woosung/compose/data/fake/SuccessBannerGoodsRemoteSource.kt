package com.woosung.compose.data.fake

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.model.BannerResponse
import com.woosung.compose.data.model.ContentsResponse
import com.woosung.compose.data.model.FooterResponse
import com.woosung.compose.data.model.GoodsContainerResponse
import com.woosung.compose.data.model.HeaderResponse
import com.woosung.compose.data.model.StyleResponse
import com.woosung.compose.data.remote.api.GoodsRemoteSource

class SuccessBannerGoodsRemoteSource : GoodsRemoteSource {

    override suspend fun getGoodsList(): NetworkResult<List<GoodsContainerResponse>> {
        return NetworkResult.Success<List<GoodsContainerResponse>>(
            listOf(
                GoodsContainerResponse(
                    contents = ContentsResponse(
                        type = "BANNER",
                        banners = listOf(
                            BannerResponse(
                                linkURL = "1",
                                thumbnailURL = "테스트 섬네일",
                                title = "",
                                description = "",
                                keyword = "",
                            ),
                            BannerResponse(
                                linkURL = "2",
                                thumbnailURL = "테스트 섬네일",
                                title = "",
                                description = "",
                                keyword = "",
                            ),
                            BannerResponse(
                                linkURL = "3",
                                thumbnailURL = "테스트 섬네일",
                                title = "",
                                description = "",
                                keyword = "",
                            ),
                            BannerResponse(
                                linkURL = "4",
                                thumbnailURL = "테스트 섬네일",
                                title = "",
                                description = "",
                                keyword = "",
                            ),
                            BannerResponse(
                                linkURL = "5",
                                thumbnailURL = "테스트 섬네일",
                                title = "",
                                description = "",
                                keyword = "",
                            )
                        ),
                        goods = listOf(),
                        styles = listOf()
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
