package com.woosung.compose.data.remote.impl

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.handle.handleApi
import com.woosung.compose.data.model.ProductResponse
import com.woosung.compose.data.network.AssignmentApi
import com.woosung.compose.data.remote.api.ProductRemoteSource
import javax.inject.Inject

class ProductRemoteSourceImp @Inject constructor(
    private val assignmentApi: AssignmentApi
) : ProductRemoteSource {
    override suspend fun getProducts(): NetworkResult<List<ProductResponse>> = handleApi{assignmentApi.getProductList()}

}
