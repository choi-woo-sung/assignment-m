package com.woosung.compose.data.repository.impl

import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.model.Page
import com.woosung.compose.data.model.toModel
import com.woosung.compose.data.remote.api.GoodsRemoteSource
import com.woosung.compose.domain.model.Content
import com.woosung.compose.domain.model.ContentType
import com.woosung.compose.domain.model.GoodsContainer
import com.woosung.compose.domain.repository.api.GoodsRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

internal class GoodsRepositoryImp @Inject constructor(
    private val goodsRemoteSource: GoodsRemoteSource
) : GoodsRepository {


    private val _goodsListFlow = MutableStateFlow<List<GoodsContainer>>(listOf())


    private val _cursorMapFlow = MutableStateFlow(
        mutableMapOf<ContentType, Page>().apply {
            put(ContentType.Grid, Page(initialSize = 6, chunkSize = 3, endOfContent = false))
            put(ContentType.Style, Page(initialSize = 6, chunkSize = 3, endOfContent = false))
        }
    )
    private val cursorMapFlow = _cursorMapFlow.asStateFlow()


    override val goodsListFlow = combine(_goodsListFlow, cursorMapFlow) { goods, cursorMap ->
        goods.groupBy { it.contents.type }.flatMap { (type, items) ->
            val page = cursorMap[type] ?: return@flatMap items

            items.mapNotNull { goods ->
                val limitedContent = when (val content = goods.contents) {
                    is Content.BannerType -> {
                        val sliced = content.data.take(page.initialSize)
                        if (sliced.isEmpty()) null else Content.BannerType(sliced)
                    }
                    is Content.GridType -> {
                        val sliced = content.data.take(page.initialSize)
                        if (sliced.isEmpty()) null else Content.GridType(sliced)
                    }
                    is Content.ScrollType -> {
                        val sliced = content.data.take(page.initialSize)
                        if (sliced.isEmpty()) null else Content.ScrollType(sliced)
                    }
                    is Content.StyleType -> {
                        val sliced = content.data.take(page.initialSize)
                        if (sliced.isEmpty()) null else Content.StyleType(sliced)
                    }
                    is Content.Unknown -> null
                }

                limitedContent?.let {
                    goods.copy(contents = it,  footer = if(page.endOfContent) null else goods.footer)
                }
            }
        }
    }


    override suspend fun getGoods() {
        when (val result = goodsRemoteSource.getGoodsList()) {
            is NetworkResult.Success -> {
                _goodsListFlow.emit(result.data.map { it.toModel() })
            }

            is NetworkResult.NetworkError -> throw Exception(result.message)
            is NetworkResult.Exception -> throw result.e
        }
    }

    override  fun appendGoods(contentType: ContentType) {
        val currentList = _goodsListFlow.value

        val page = _cursorMapFlow.value[contentType] ?: return
        if (page.endOfContent) return

        val targetGoods = currentList.find { it.contents.type == contentType } ?: return

        val contentSize = when (val content = targetGoods.contents) {
            is Content.BannerType -> content.data.size
            is Content.GridType -> content.data.size
            is Content.ScrollType -> content.data.size
            is Content.StyleType -> content.data.size
            is Content.Unknown -> 0
        }

        val newSize = page.initialSize + page.chunkSize
        val newEndOfContent = newSize >= contentSize

        val newMap = _cursorMapFlow.value.toMutableMap()
        newMap[contentType] = page.copy(
            initialSize = newSize.coerceAtMost(contentSize),
            endOfContent = newEndOfContent
        )
        _cursorMapFlow.value = newMap
    }
}
