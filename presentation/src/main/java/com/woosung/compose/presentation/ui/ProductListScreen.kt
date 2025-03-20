package com.woosung.compose.presentation.ui

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.airbnb.mvrx.compose.collectAsState
import com.airbnb.mvrx.compose.mavericksViewModel
import com.woosung.compose.domain.model.FooterType
import com.woosung.compose.presentation.component.MsFooterButton
import com.woosung.compose.presentation.component.MsHeader
import com.woosung.compose.presentation.component.ProductItem
import com.woosung.compose.presentation.component.content.BannerContent
import com.woosung.compose.presentation.component.content.ScrollContent
import com.woosung.compose.presentation.component.content.StyleContent
import com.woosung.compose.presentation.model.ContentUI
import com.woosung.compose.presentation.model.ProductUi
import com.woosung.compose.presentation.ui.common.ErrorScreen
import com.woosung.compose.presentation.ui.common.LoadingScreen
import com.woosung.compose.presentation.util.ObserveAsEvents

@Composable
internal fun ProductListScreen(viewModel: ProductListViewModel = mavericksViewModel()) {
    val uiState by viewModel.collectAsState()
    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.sideEffect) { event ->
        when (event) {
            is ProductListSideEffect.NavigateToWebSite -> {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(event.linkUrl))
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    ProductListScreen(uiState, rememberProductListUiActions(viewModel = viewModel))
}


@Composable
private fun ProductListScreen(
    productListUiState: ProductListUiState,
    productListUiActions: ProductListUiActions
) {
    // 로딩 화면
    if (productListUiState.isLoading) {
        LoadingScreen()
    } else if (productListUiState.isError) {
        ErrorScreen { productListUiActions.onRetry() }
    } else {
        ProductListSuccessScreen(productListUiState.productList, productListUiActions)
    }


}

@Composable
private fun ProductListSuccessScreen(
    productList: List<ProductUi>,
    productListUiActions: ProductListUiActions
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
    ) {
        productList.forEach {
            ProductListItem(it, productListUiActions)
        }
    }
}

internal fun LazyGridScope.ProductListItem(
    product: ProductUi,
    productListUiActions: ProductListUiActions
) {
    // Product Header
    product.contents.header?.let { header ->
        item(span = { GridItemSpan(3) }) {
            MsHeader(header)
        }
    }

    ProductContent(product.contents, productListUiActions)

    // Product Footer
    product.contents.footer?.let { footer ->
        item(span = { GridItemSpan(3) }) {
            MsFooterButton(
                footer,
                onClicked = {
                    when (it) {
                        FooterType.REFRESH -> productListUiActions.onRecommand(product.uuid)
                        FooterType.MORE -> productListUiActions.onLoadMore(product.uuid)
                    }
                }
            )
        }
    }

}


internal fun LazyGridScope.ProductContent(
    content: ContentUI,
    productListUiActions: ProductListUiActions
) {
    when (content) {
        is ContentUI.BannerContent -> {
            item(span = { GridItemSpan(3) }) {
                BannerContent(content.bannerUi, onClicked = {
                    productListUiActions.onProductClick(linkUrl = it)
                })
            }
        }

        is ContentUI.GridContent -> {
            items(content.goodUi) {
                ProductItem(modifier = Modifier.clickable {
                    productListUiActions.onProductClick(linkUrl = it.linkURL)
                }, good = it)
            }
        }

        is ContentUI.StyleContent -> {
            item(span = { GridItemSpan(3) }) {
                StyleContent(styleList = content.styleUi, onClicked = {
                    productListUiActions.onProductClick(linkUrl = it)
                })
            }

        }

        is ContentUI.ScrollContent -> {
            item(span = { GridItemSpan(3) }) {
                ScrollContent(content.goodUi, onClicked = {
                    productListUiActions.onProductClick(linkUrl = it)
                })
            }
        }

        ContentUI.Unknown -> {
            // Do nothing
        }
    }

}
