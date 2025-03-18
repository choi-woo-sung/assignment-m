package com.woosung.compose.presentation.screen

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.layout.ContentScale
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.woosung.compose.domain.model.Content
import com.woosung.compose.domain.model.Product
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.component.MsFooterButton
import com.woosung.compose.presentation.component.MsHeader
import com.woosung.compose.presentation.component.ProductItem
import com.woosung.compose.presentation.component.content.BannerContent
import com.woosung.compose.presentation.component.content.ScrollContent
import com.woosung.compose.presentation.component.content.StyleContent
import com.woosung.compose.presentation.util.debugPlaceholder

@Composable
internal fun ProductListScreen(viewModel: ProductListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProductListScreen(uiState)
}


@Composable
private fun ProductListScreen(productListUiState: ProductListUiState) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        productListUiState.productList.forEach {
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
            StyleContent(content.data)

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
