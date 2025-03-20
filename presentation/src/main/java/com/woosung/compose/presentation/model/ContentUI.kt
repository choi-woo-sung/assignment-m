package com.woosung.compose.presentation.model

import androidx.compose.runtime.Stable
import com.woosung.compose.domain.model.Banner
import com.woosung.compose.domain.model.Content
import com.woosung.compose.domain.model.Footer
import com.woosung.compose.domain.model.FooterType
import com.woosung.compose.domain.model.Good
import com.woosung.compose.domain.model.Header
import com.woosung.compose.domain.model.Product
import com.woosung.compose.domain.model.Style
import com.woosung.compose.presentation.model.ContentUI.*
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import java.lang.Integer.min
import java.util.UUID


data class ProductUi(
    val uuid: String = UUID.randomUUID().toString(),
    val contents: ContentUI,
)

@Stable
sealed class ContentUI(
    open val header: HeaderUi? = null,
    open val footer: FooterUi? = null
) {


    data class StyleContent(
        val styleUi: ImmutableList<StyleUi>,
        val extraList: ImmutableList<ImmutableList<StyleUi>> = persistentListOf(),
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI() {
        val isMore: Boolean = extraList.isNotEmpty()
    }

    data class BannerContent(
        val bannerUi: ImmutableList<BannerUi>,
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI()
    data class ScrollContent(
        val goodUi: ImmutableList<GoodUi>,
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI()

    data class GridContent(
        val goodUi: ImmutableList<GoodUi>,
        val extraList: ImmutableList<ImmutableList<GoodUi>> = persistentListOf(),
        override val header: HeaderUi? = null,
        override val footer: FooterUi? = null,
    ) : ContentUI() {
        val isMore: Boolean = extraList.isNotEmpty()
    }

    object Unknown : ContentUI()
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

data class GoodUi(
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

fun Good.toUiModel() = GoodUi(
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


fun Content.toUiModel(): ContentUI = when (this) {
    is Content.BannerType -> BannerContent(
        this.data.map { it.toUiModel() }.toImmutableList(),
        header = product.header?.toUiModel(),
        footer = product.footer?.toUiModel(),
    )

    is Content.GridType -> {
        val item = this.data.map { it.toUiModel() }.chunkItems(6, 3).toImmutableList()
        GridContent(
            goodUi = item[0].toImmutableList(),
            extraList = item.drop(1).map { it.toImmutableList() }.toImmutableList(),
        )
    }

    is Content.ScrollType -> ScrollContent(
        goodUi = this.data.map { it.toUiModel() }.toImmutableList(),
    )

    is Content.StyleType -> {
        val item = this.data.map { it.toUiModel() }.chunkItems(6, 3)
        StyleContent(
            styleUi = item[0].toImmutableList(),
            extraList = item.drop(1).map { it.toImmutableList() }.toImmutableList(),
        )
    }

    Content.Unknown -> Unknown
}

fun List<Product>.toUiModel(): ImmutableList<ProductUi> = this.map { product ->
    ProductUi(
        contents = product.contents.toUiModel(),
    )
}.toImmutableList()


/**
 * 요구사항에 맞추기위해 청크를 하는 함수
 *
 * @param items
 * @param firstChunkSize
 * @param otherChunkSize
 * @return
 */
fun <T> List<T>.chunkItems(firstChunkSize: Int, otherChunkSize: Int): List<List<T>> {
    val result = mutableListOf<List<T>>()
    var index = 0

    if (this.isNotEmpty()) {
        result.add(this.subList(index, min(index + firstChunkSize, this.size)))
        index += firstChunkSize

        while (index < this.size) {
            result.add(this.subList(index, min(index + otherChunkSize, this.size)))
            index += otherChunkSize
        }
    }

    return result
}
