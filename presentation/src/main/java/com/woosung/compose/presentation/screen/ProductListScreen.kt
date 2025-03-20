package com.woosung.compose.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woosung.compose.domain.model.Content
import com.woosung.compose.domain.model.Product
import com.woosung.compose.presentation.component.MsFooterButton
import com.woosung.compose.presentation.component.MsHeader
import com.woosung.compose.presentation.component.ProductItem
import com.woosung.compose.presentation.component.content.BannerContent
import com.woosung.compose.presentation.component.content.ScrollContent
import com.woosung.compose.presentation.component.content.StyleContent
import com.woosung.compose.presentation.screen.ProductListViewModel.ProductListUiState
import com.woosung.compose.presentation.screen.common.ErrorScreen
import com.woosung.compose.presentation.screen.common.LoadingScreen

@Composable
internal fun ProductListScreen(viewModel: ProductListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()


    when (val state = uiState) {
        is ProductListUiState.Loading -> {
            LoadingScreen()
        }

        is ProductListUiState.Success -> {
            ProductListScreen(state.productList)
        }

        is ProductListUiState.Error -> {
            ErrorScreen() {}
        }
    }
}


@Composable
private fun ProductListScreen(productList: List<Product>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3),
        ) {
        productList.forEach {
            ProductListItem(it)
        }
    }
}

fun LazyGridScope.ProductListItem(product: Product) {
    // Product Header
    product.header?.let { header ->
        item(span = { GridItemSpan(3) }) {
            MsHeader(header)
        }
    }

    // Product Contents
    ProductContent(product.contents)
    // Product Footer
    product.footer?.let { footer ->
        item(span = { GridItemSpan(3) }) {
            MsFooterButton(footer)
        }
    }

}


fun LazyGridScope.ProductContent(content: Content) {
    when (content) {
        is Content.BannerType -> {
            item(span = { GridItemSpan(3) }) {
                BannerContent(content.data)
            }
        }

        is Content.GridType -> {
            items(content.data) {
                ProductItem(good = it)
            }
        }

        is Content.StyleType -> {
            item(span = { GridItemSpan(3) }) {
                StyleContent(styleList = content.data)
            }

        }

        is Content.ScrollType -> {
            item(span = { GridItemSpan(3) }) {
                ScrollContent(content.data)
            }
        }

        Content.Unknown -> {
            // Do nothing
        }
    }

}
