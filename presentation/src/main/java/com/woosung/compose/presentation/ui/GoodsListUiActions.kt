package com.woosung.compose.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal interface GoodsListUiActions {
    fun onRetry()
    fun onRecommand(uuid:String)
    fun onGoodsClicked(linkUrl: String)
    fun onLoadMore(uuid: String)
    fun onUnknown()
}


@Composable
internal fun rememberGoodsListUiActions(viewModel: GoodsListViewModel) = remember {
    object : GoodsListUiActions {
        override fun onRetry() {
            viewModel.fetchGoods()
        }

        override fun onRecommand(uuid: String) {
            viewModel.shuffleGoodsList(uuid)
        }

        override fun onGoodsClicked(linkUrl: String) {
            viewModel.navigateGoodsDetail(linkUrl)
        }

        override fun onLoadMore(uuid: String) {
            viewModel.loadMoreGoods(uuid)
        }

        override fun onUnknown() {
            viewModel.executeUnknown()
        }
    }
}
