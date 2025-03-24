package com.woosung.compose.presentation.ui

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement.spacedBy
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.woosung.compose.domain.model.FooterType
import com.woosung.compose.presentation.component.GoodsItem
import com.woosung.compose.presentation.component.Header
import com.woosung.compose.presentation.component.MsFooterButton
import com.woosung.compose.presentation.component.content.BannerContent
import com.woosung.compose.presentation.component.content.ScrollContent
import com.woosung.compose.presentation.component.content.StyleContent
import com.woosung.compose.presentation.model.ContentUI
import com.woosung.compose.presentation.theme.MsPadding
import com.woosung.compose.presentation.ui.common.ErrorScreen
import com.woosung.compose.presentation.ui.common.LoadingScreen
import com.woosung.compose.presentation.util.ObserveAsEvents

@Composable
internal fun GoodsListScreen(viewModel: GoodsListViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    ObserveAsEvents(flow = viewModel.sideEffect) { event ->
        when (event) {
            is GoodsListSideEffect.NavigateToWebSite -> {
                try {
                    val intent = Intent(Intent.ACTION_VIEW, event.linkUrl.toUri())
                    context.startActivity(intent)
                } catch (e: Exception) {
                    Toast.makeText(context, e.message, Toast.LENGTH_SHORT).show()
                }
            }

            is GoodsListSideEffect.ToastMessage -> {
                Toast.makeText(context, event.message, Toast.LENGTH_SHORT).show()
            }
        }
    }
    GoodsListScreen(uiState, rememberGoodsListUiActions(viewModel = viewModel))
}


@Composable
private fun GoodsListScreen(
    goodsListUiState: GoodsListUiState,
    goodsListUiActions: GoodsListUiActions
) {
    // 로딩 화면
    if (goodsListUiState.isLoading) {
        LoadingScreen()
    } else if (goodsListUiState.isError) {
        ErrorScreen { goodsListUiActions.onRetry() }
    } else {
        GoodsListSuccessScreen(
            goodsListUiState.contentsList,
            goodsClicked = { goodsListUiActions.onGoodsClicked(it) },
            headerClicked = { goodsListUiActions.onGoodsClicked(it) },
            footerClicked = { footerType, contentUi ->
                when (footerType) {
                    FooterType.REFRESH -> goodsListUiActions.onRecommand(contentUi.uuid)
                    FooterType.MORE -> goodsListUiActions.onLoadMore(contentUi.uuid)
                    FooterType.UNKNOWN -> goodsListUiActions.onUnknown()
                }
            },
        )
    }


}

@Composable
private fun GoodsListSuccessScreen(
    contentUiList: List<ContentUI>,
    goodsClicked: (String) -> Unit = {},
    headerClicked: (String) -> Unit = {},
    footerClicked: (FooterType, ContentUI) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        horizontalArrangement = spacedBy(MsPadding.small),
        verticalArrangement = spacedBy(MsPadding.small),
    ) {
        contentUiList.forEach { contentUi ->
            GoodsListItem(
                contentUi,
                goodsClicked,
                headerClicked,
                footerClicked = { footerClicked(it, contentUi) }
            )
        }
    }
}

internal fun LazyGridScope.GoodsListItem(
    contentUi: ContentUI,
    goodsClicked: (String) -> Unit = {},
    headerClicked: (String) -> Unit = {},
    footerClicked: (FooterType) -> Unit = {}
) {
    // Product Header
    contentUi.header?.let { header ->
        item(span = { GridItemSpan(3) }) {
            Header(header, actionClicked = headerClicked)
        }
    }

    GoodsContent(contentUi, goodsClicked)

    // Product Footer
    contentUi.footer?.let { footer ->
        item(span = { GridItemSpan(3) }) {
            MsFooterButton(
                modifier = Modifier.padding(horizontal = MsPadding.medium),
                footer,
                onClicked = footerClicked
            )
        }
    }

}


internal fun LazyGridScope.GoodsContent(
    content: ContentUI,
    goodsClicked: (String) -> Unit = {}
) {
    when (content) {
        is ContentUI.BannerContent -> {
            item(span = { GridItemSpan(3) }) {
                BannerContent(content.bannerUi, onClicked = {
                    goodsClicked(it)
                })
            }
        }

        is ContentUI.GridContent -> {
            items(content.goodUi) {
                GoodsItem(modifier = Modifier.clickable {
                    goodsClicked(it.linkURL)

                }, good = it)
            }
        }

        is ContentUI.StyleContent -> {
            item(span = { GridItemSpan(3) }) {
                StyleContent(styleList = content.styleUi, onClicked = {
                    goodsClicked(it)
                })
            }

        }

        is ContentUI.ScrollContent -> {
            item(span = { GridItemSpan(3) }) {
                ScrollContent(content.goodUi, onClicked = {
                    goodsClicked(it)
                })
            }
        }

        ContentUI.Unknown -> {
            // Do nothing
        }
    }

}


