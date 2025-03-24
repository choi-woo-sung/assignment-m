package com.woosung.compose.presentation.model

import androidx.compose.runtime.Stable
import com.woosung.compose.domain.model.Banner
import com.woosung.compose.domain.model.Content
import com.woosung.compose.domain.model.ContentType
import com.woosung.compose.domain.model.Footer
import com.woosung.compose.domain.model.FooterType
import com.woosung.compose.domain.model.Goods
import com.woosung.compose.domain.model.Header
import com.woosung.compose.domain.model.GoodsContainer
import com.woosung.compose.domain.model.Style
import com.woosung.compose.presentation.model.ContentUI.*
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import java.util.UUID


@Stable
sealed class ContentUI(
    open val header: HeaderUi? = null,
    open val footer: FooterUi? = null,
    val contentType: ContentType
) {

    val uuid: String = UUID.randomUUID().toString()

    data class StyleContent(
        val styleUi: ImmutableList<StyleUi>,
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI(contentType = ContentType.Style)

    data class BannerContent(
        val bannerUi: ImmutableList<BannerUi>,
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI(contentType = ContentType.Banner)

    data class ScrollContent(
        val goodUi: ImmutableList<GoodsUi>,
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI(contentType = ContentType.Scroll)

    data class GridContent(
        val goodUi: ImmutableList<GoodsUi>,
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI(contentType = ContentType.Grid)

    object Unknown : ContentUI(contentType = ContentType.Unknown)
}

data class HeaderUi(
    val title: String,
    val iconURL: String? = null,
    val linkURL: String? = null,
)

data class BannerUi(
    val linkURL: String,
    val thumbnailURL: String,
    val title: String,
    val description: String,
    val keyword: String,
)

data class GoodsUi(
    val linkURL: String,
    val thumbnailURL: String,
    val brandName: String,
    val price: Long,
    val saleRate: Long,
    val hasCoupon: Boolean,
)

data class FooterUi(
    val type: FooterType,
    val title: String,
    val iconURL: String? = null,
)

data class StyleUi(
    val linkURL: String,
    val thumbnailURL: String,
)

fun Header.toUiModel() = HeaderUi(
    title = title,
    iconURL = iconURL,
    linkURL = linkURL,
)

fun Goods.toUiModel() = GoodsUi(
    linkURL = linkURL,
    thumbnailURL = thumbnailURL,
    brandName = brandName,
    price = price,
    saleRate = saleRate,
    hasCoupon = hasCoupon,

    )

fun Style.toUiModel() = StyleUi(
    linkURL = linkURL,
    thumbnailURL = thumbnailURL,
)

fun Footer.toUiModel() = FooterUi(
    type = type,
    title = title,
    iconURL = iconURL,

    )

fun Banner.toUiModel() = BannerUi(
    linkURL = linkURL,
    thumbnailURL = thumbnailURL,
    title = title,
    description = description,
    keyword = keyword,

    )


fun GoodsContainer.toUiModel(): ContentUI = when (val content = this.contents) {
    is Content.BannerType -> BannerContent(
        content.data.map { it.toUiModel() }.toImmutableList(),
        header = this.header?.toUiModel(),
        footer = if (this.contents.endOfContent) null else this.footer?.toUiModel(),
    )

    is Content.GridType -> {
        GridContent(
            goodUi = content.data.map { it.toUiModel() }.toImmutableList(),
            header = this.header?.toUiModel(),
            footer = if (this.contents.endOfContent) null else this.footer?.toUiModel(),
        )
    }

    is Content.ScrollType -> ScrollContent(
        goodUi = content.data.map { it.toUiModel() }.toImmutableList(),
        header = this.header?.toUiModel(),
        footer = if (this.contents.endOfContent) null else this.footer?.toUiModel(),
    )

    is Content.StyleType -> {
        StyleContent(
            styleUi = content.data.map { it.toUiModel() }.toImmutableList(),
            header = this.header?.toUiModel(),
            footer = if (this.contents.endOfContent) null else this.footer?.toUiModel(),
        )
    }

    Content.Unknown -> Unknown
}


