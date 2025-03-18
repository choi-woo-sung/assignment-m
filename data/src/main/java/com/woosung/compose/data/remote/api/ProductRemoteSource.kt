package com.woosung.compose.data.remote.api

import com.woosung.compose.data.model.ProductResponse

interface ProductRemoteSource {
    suspend fun getProducts(): List<ProductResponse>
}
