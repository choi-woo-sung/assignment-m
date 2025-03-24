package com.woosung.compose.data.remote.impl

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.handle.handleApi
import com.woosung.compose.data.model.GoodsContainerResponse
import com.woosung.compose.data.network.AssignmentApi
import com.woosung.compose.data.remote.api.GoodsRemoteSource
import javax.inject.Inject

class GoodsRemoteSourceImp @Inject constructor(
    private val assignmentApi: AssignmentApi
) : GoodsRemoteSource {
    override suspend fun getGoodsList(): NetworkResult<List<GoodsContainerResponse>> =
        handleApi { assignmentApi.getGoodsList() }
}
