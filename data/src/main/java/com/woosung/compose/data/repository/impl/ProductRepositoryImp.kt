package com.woosung.compose.data.repository.impl

import com.woosung.compose.data.remote.api.ProductRemoteSource
import com.woosung.compose.domain.repository.api.ProductRepository
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val productRemoteSource: ProductRemoteSource
) : ProductRepository {
    suspend fun getProducts() = productRemoteSource.getProducts()
}
