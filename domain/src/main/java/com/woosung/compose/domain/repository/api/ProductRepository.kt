package com.woosung.compose.domain.repository.api

import com.woosung.compose.domain.model.Product

interface ProductRepository {
    suspend fun getProducts(): List<Product>
}
