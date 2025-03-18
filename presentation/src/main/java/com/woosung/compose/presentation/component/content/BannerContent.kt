package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.woosung.compose.domain.model.Banner
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.util.debugPlaceholder
import com.woosung.compose.test.ui.theme.MSGray


@Composable
fun BannerContent(bannerList: List<Banner>, textColor: Color = Color.White) {
    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bannerList.size }
    )
    HorizontalPager(state = pagerState) { page ->
        Box() {
            AsyncImage(
                model = bannerList[page].thumbnailURL,
                placeholder = debugPlaceholder(R.drawable.test),
                contentDescription = ""
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = bannerList[page].title,
                color = textColor
            )
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .absoluteOffset(y = 30.dp),
                text = bannerList[page].keyword + " | " + bannerList[page].description,
                color = textColor
            )

            Box(
                modifier = Modifier
                    .background(color = MSGray , shape = RoundedCornerShape(5))
                    .align(Alignment.BottomEnd)
                    .padding(horizontal = 20.dp, vertical = 4.dp)
            ) {
                Text(
                    modifier = Modifier.align(Alignment.BottomEnd),
                    text = "${page}/${bannerList.size}",
                    color = textColor
                )
            }
        }
    }
}


@Preview
@Composable
private fun BannerContentPreview() {
    BannerContent(bannerListPreview)
}


val bannerListPreview =
    listOf(
        Banner(
            linkURL = "",
            thumbnailURL = "",
            title = "테스트 1",
            description = "테스트 디스크립션",
            keyword = "할인판매"
        ),
        Banner(
            linkURL = "",
            thumbnailURL = "",
            title = "테스트 1",
            description = "테스트 디스크립션",
            keyword = "할인판매"
        )
    )