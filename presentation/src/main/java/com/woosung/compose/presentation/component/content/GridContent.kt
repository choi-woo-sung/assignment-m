package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import com.woosung.compose.domain.model.Good
import com.woosung.compose.presentation.component.ProductItem


@Composable
fun GridContent(goodsList: List<Good>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        items(items = goodsList) {
            ProductItem(good = it)
        }

    }
}

