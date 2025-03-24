package com.woosung.compose.data.network

import com.woosung.compose.data.model.GoodsContainerResponse
import com.woosung.compose.data.handle.ApiResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface AssignmentApi {

    /**
     * 상품 리스트를 가져옵니다.
     *
     * @return
     */
    @GET("/interview/list.json")
    suspend fun getGoodsList(): Response<ApiResponse<List<GoodsContainerResponse>>>
}
