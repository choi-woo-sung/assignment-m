package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import com.woosung.compose.domain.model.Good
import com.woosung.compose.presentation.component.ProductItem

@Composable
fun ScrollContent(listItem: List<Good>) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { listItem.size }
    )
    HorizontalPager(state = pagerState) { page ->
        ProductItem(listItem[page])
    }
}
