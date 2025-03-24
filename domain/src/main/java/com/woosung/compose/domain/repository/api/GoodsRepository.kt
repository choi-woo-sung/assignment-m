package com.woosung.compose.domain.repository.api

import com.woosung.compose.domain.model.ContentType
import com.woosung.compose.domain.model.GoodsContainer
import kotlinx.coroutines.flow.Flow

interface GoodsRepository {
    val goodsListFlow: Flow<List<GoodsContainer>>

    suspend fun getGoods(): Unit

     fun appendGoods(type: ContentType)
}
