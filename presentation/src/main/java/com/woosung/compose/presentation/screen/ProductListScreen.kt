package com.woosung.compose.presentation.screen

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
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
    LazyColumn  {
        productListUiState.productList.forEach {
            ProductListItem(it)
        }
    }
}

fun LazyListScope.ProductListItem(product: Product) {
    // Product Header
    product.header?.let { header ->
        item {
            MsHeader(header)
        }
    }

    // Product Contents
    ProductContent(product.contents)
    // Product Footer
    product.footer?.let { footer ->
        item {
            MsFooterButton(footer)
        }
    }

}


fun LazyListScope.ProductContent(content: Content) {
    when (content) {
        is Content.BannerType -> {
            item {
                BannerContent(content.data)
            }
        }

        is Content.GridType -> {
            items(content.data) {
                ProductItem(good = it)
            }
        }

        is Content.StyleType -> {
            item(){
                StyleContent(styleList = content.data)
            }

        }

        is Content.ScrollType -> {
            item {
                ScrollContent(content.data)
            }
        }

        Content.Unknown -> {
            // Do nothing
        }
    }

}
