package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.woosung.compose.domain.model.Banner


@Composable
fun BannerContent(bannerList: List<Banner>) {
    val pager = rememberPagerState(
        initialPage = 0,
        pageCount = {bannerList.size}
    )
    HorizontalPager() { }
}
