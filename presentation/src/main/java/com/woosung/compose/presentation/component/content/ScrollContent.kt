package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woosung.compose.domain.model.Good
import com.woosung.compose.presentation.component.ProductItem
import com.woosung.compose.presentation.model.GoodUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun ScrollContent(listItem: ImmutableList<GoodUi>, onClicked: (String) -> Unit = {}) {
    val state = rememberLazyListState()
    LaunchedEffect(listItem) {
        state.scrollToItem(0)
    }

    LazyRow(
        state = state,
        contentPadding = PaddingValues(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        items(
            items = listItem,
            key = { it.linkURL }) {
            ProductItem(modifier = Modifier
                .animateItem()
                .clickable { onClicked(it.linkURL) }, it)
        }
    }
}


@Preview
@Composable
private fun ScrollContentPreview() {
    ScrollContent(
        listItem = persistentListOf(
            GoodUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodUi(
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
