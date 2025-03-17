package com.woosung.compose.domain.repository.api

import com.woosung.compose.domain.model.Main

interface ProductRepository {
    suspend fun getProducts(): List<Main>
}
