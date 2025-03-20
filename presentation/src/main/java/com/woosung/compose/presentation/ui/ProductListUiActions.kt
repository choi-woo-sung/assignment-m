package com.woosung.compose.presentation.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal interface ProductListUiActions {
    fun onRetry()
    fun onRecommand(uuid:String)
    fun onProductClick(linkUrl: String)
    fun onLoadMore(uuid: String)
}


// 왜 썼는지 파악할것
@Composable
internal fun rememberProductListUiActions(viewModel: ProductListViewModel) = remember {
    object : ProductListUiActions {
        override fun onRetry() {
        }

        override fun onRecommand(uuid: String) {
            viewModel.shuffleProductList(uuid)
        }

        override fun onProductClick(linkUrl: String) {
            viewModel.navigateProductDetail(linkUrl)
        }

        override fun onLoadMore(uuid: String) {
            viewModel.loadMoreProduct(uuid)
        }
    }
}
