package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woosung.compose.domain.model.Good
import com.woosung.compose.presentation.component.ProductItem


@Composable
fun LazyGridScope.GridContent(goodsList: List<Good>) {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp),
//    ) {
//        items(items = goodsList, key = { it.hashCode() }) {
//            ProductItem(good = it)
//        }
//    }
    items(goodsList) {
        ProductItem(good = it)
    }
}

@Composable
fun GridContent(goodsList: List<Good>) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 20.dp),
    ) {
        items(items = goodsList, key = { it.hashCode() }) {
            ProductItem(good = it)
        }
    }
}





@Preview
@Composable
private fun GridContentPreview() {
    GridContent(
        goodsList = listOf(
            Good(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            Good(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            Good(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            Good(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            Good(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            Good(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            )
        )
    )
}
