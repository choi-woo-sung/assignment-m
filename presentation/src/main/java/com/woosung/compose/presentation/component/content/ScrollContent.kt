package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.woosung.compose.presentation.component.GoodsItem
import com.woosung.compose.presentation.model.GoodsUi
import com.woosung.compose.presentation.theme.MsPadding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun ScrollContent(listItem: ImmutableList<GoodsUi>, onClicked: (String) -> Unit = {}) {
    val state = rememberLazyListState()
    LaunchedEffect(listItem) {
        state.scrollToItem(0)
    }

    LazyRow(
        state = state,
        horizontalArrangement = Arrangement.spacedBy(MsPadding.small),
    ) {
        items(
            items = listItem,
            key = { it.linkURL }) {
            GoodsItem(modifier = Modifier.width(120.dp)
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
            GoodsUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodsUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodsUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodsUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodsUi(
                thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                brandName = "BrandName",
                price = 10000,
                saleRate = 10,
                hasCoupon = true,
                linkURL = ""
            ),
            GoodsUi(
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
