package com.woosung.compose.presentation.component.content

import android.text.style.TtsSpan.TextBuilder
import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.BoxWithConstraintsScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.woosung.compose.domain.model.Banner
import com.woosung.compose.presentation.component.ParallaxImage
import com.woosung.compose.presentation.model.BannerUi
import com.woosung.compose.presentation.util.ImageUtil
import com.woosung.compose.test.ui.theme.MSGray
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


@Composable
fun BannerContent(
    bannerList: List<BannerUi>,
    textColor: Color = Color.White,
    onClicked : (String) -> Unit = {}
) {

    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()




    val pagerState = rememberPagerState(
        initialPage = 0,
        pageCount = { bannerList.size }
    )

    LaunchedEffect(true) {
        while (pagerState.currentPage != bannerList.size - 1) {
            delay(3000)
            pagerState.animateScrollToPage(
                page = pagerState.settledPage + 1,
                animationSpec = tween(
                    durationMillis = 1000,
                ),
            )
        }
    }

    Box() {
        HorizontalPager(
            modifier = Modifier.height(400.dp),
            state = pagerState,
            beyondViewportPageCount = 1
        ) { currentPage ->

            var imageBitmap by remember { mutableStateOf<ImageBitmap?>(null) }

            LaunchedEffect(currentPage) {
                ImageUtil.loadImageBitmap(
                    context,
                    coroutineScope,
                    bannerList[currentPage].thumbnailURL
                ) {
                    imageBitmap = it.asImageBitmap()
                }
            }

            val currentPageOffset = calculatePageOffset(pagerState, currentPage)


            BoxWithConstraints (modifier = Modifier.clickable{onClicked(bannerList[currentPage].linkURL)}) {
                imageBitmap?.let {
                    ParallaxImage(it, currentPageOffset)
                }
                BannerOverlay(bannerList[currentPage], textColor, currentPageOffset)
            }
        }
        Box(
            modifier = Modifier
                .width(80.dp)
                .background(color = Color.Black.copy(alpha = 0.7f), shape = RoundedCornerShape(5))
                .align(Alignment.BottomEnd)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center).padding(vertical = 2.dp),
                text = "${pagerState.currentPage + 1} / ${bannerList.size}",
                color = textColor
            )
        }
    }
}

@Composable
private fun BoxWithConstraintsScope.BannerOverlay(
    banner: BannerUi,
    textColor: Color,
    currentPageOffset: Float,
) {

    val width = constraints.maxWidth.toFloat()

    val titleTranslationX = lerp(-width, 0f, 1f - currentPageOffset.coerceIn(-1f, 1f))

//    val descriptionTranslationX = lerp(150f, 0f, 1f - currentPageOffset.coerceIn(-1f, 1f))


    Column(
        Modifier.align(Alignment.BottomCenter).absoluteOffset(y = -50.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .graphicsLayer {
                    translationX = titleTranslationX
                },
            text = banner.title,
            color = textColor,
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(modifier = Modifier.height(30.dp))
        Text(
            modifier = Modifier
                .graphicsLayer {
                    translationX = currentPageOffset
                },
            text = buildString {
                append(banner.description)
                if (banner.keyword.isNotEmpty()) {
                    append(" | ")
                    append(banner.keyword)
                }
            },
            color = textColor,
            style =  MaterialTheme.typography.bodySmall
        )
    }
}


fun calculatePageOffset(state: PagerState, currentPage: Int): Float {
    return (state.currentPage + state.currentPageOffsetFraction - currentPage).coerceIn(-1f, 1f)
}


@Preview
@Composable
private fun BannerContentPreview() {
    BannerContent(bannerListPreview)
}


val bannerListPreview =
    listOf(
        BannerUi(
            linkURL = "",
            thumbnailURL = "",
            title = "테스트 1",
            description = "테스트 디스크립션",
            keyword = "할인판매"
        ),
        BannerUi(
            linkURL = "",
            thumbnailURL = "",
            title = "테스트 1",
            description = "테스트 디스크립션",
            keyword = "할인판매"
        )
    )
