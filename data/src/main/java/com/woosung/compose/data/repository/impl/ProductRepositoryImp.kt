package com.woosung.compose.data.repository.impl

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.model.toModel
import com.woosung.compose.data.remote.api.ProductRemoteSource
import com.woosung.compose.domain.model.Product
import com.woosung.compose.domain.repository.api.ProductRepository
import javax.inject.Inject

class ProductRepositoryImp @Inject constructor(
    private val productRemoteSource: ProductRemoteSource
) : ProductRepository {
    override suspend fun getProducts(): Result<List<Product>> =
        
        when (val result = productRemoteSource.getProducts()) {
            is NetworkResult.Success -> Result.success(result.data.map { it.toModel() })
            is NetworkResult.NetworkError -> Result.failure(Exception(result.message))
            is NetworkResult.Exception -> Result.failure(result.e)
        }
}
