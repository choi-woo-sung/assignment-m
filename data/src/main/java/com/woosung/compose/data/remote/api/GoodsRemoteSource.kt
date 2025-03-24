package com.woosung.compose.data.remote.api

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.model.GoodsContainerResponse

internal interface GoodsRemoteSource {
    suspend fun getGoodsList():  NetworkResult<List<GoodsContainerResponse>>
}
