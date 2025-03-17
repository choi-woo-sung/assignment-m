package com.woosung.compose.data.remote.api

import com.woosung.compose.data.model.MainResponse

interface ProductRemoteSource {
    suspend fun getProducts(): List<MainResponse>
}
