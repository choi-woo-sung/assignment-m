package com.woosung.compose.data.remote.api

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.model.ProductResponse

interface ProductRemoteSource {
    suspend fun getProducts():  NetworkResult<List<ProductResponse>>
}
