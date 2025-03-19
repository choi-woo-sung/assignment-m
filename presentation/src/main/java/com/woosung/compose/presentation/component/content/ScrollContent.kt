package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.woosung.compose.domain.model.Good
import com.woosung.compose.presentation.component.ProductItem

@Composable
fun ScrollContent(listItem: List<Good>) {
    LazyRow {
        items(items = listItem){
            ProductItem(it)
        }
    }
}


@Preview
@Composable
private fun ScrollContentPreview() {
    ScrollContent(
        listItem = listOf(
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
