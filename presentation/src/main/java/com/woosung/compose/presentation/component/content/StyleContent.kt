package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Constraints
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.LayoutDirection
import coil.compose.AsyncImage
import com.woosung.compose.domain.model.Style
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.util.debugPlaceholder
import kotlin.math.roundToInt

@Composable
fun StyleContent(
    styleList: List<Style>
) {
    MusinsaGrid(
        contentPadding = PaddingValues()
    ) {
        styleList.forEach { style ->
            Box(
            ) {
                AsyncImage(
                    modifier = Modifier.fillMaxSize(),
                    model = style.thumbnailURL,
                    contentDescription = "",
                    placeholder = debugPlaceholder(
                        R.drawable.test
                    ),
                )
            }

        }
    }
}

@Composable
fun MusinsaGrid(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues,
    content: @Composable () -> Unit
) {
    Layout(
        measurePolicy = rememberMeasurePolicy(
            contentPadding = contentPadding
        ),
        content = content
    )
}

@Composable
private fun rememberMeasurePolicy(
    contentPadding: PaddingValues
): MeasurePolicy = remember(contentPadding) {
    GalleryGridMeasurePolicy(contentPadding)
}

private class GalleryGridMeasurePolicy(
    private val contentPadding: PaddingValues
) : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        val start =
            contentPadding.calculateLeftPadding(LayoutDirection.Ltr).roundToPx()
        val top = contentPadding.calculateTopPadding().roundToPx()
        val end = contentPadding.calculateRightPadding(LayoutDirection.Ltr).roundToPx()
        val bottom = contentPadding.calculateBottomPadding().roundToPx()

        val width = constraints.maxWidth
        val itemWidth = (width - start - end) / GalleryGridDefaults.SpanCount
        val firstItemWidth = itemWidth * GalleryGridDefaults.FirstItemSpanCount

        var height = top
        var rowHeight = 0
        val itemHeight = (itemWidth / GalleryGridDefaults.AspectRatio).roundToInt()

        val placeablesWithOffset = measurables
            .mapIndexed { index, measurable ->
                when (index) {
                    // 1 row, 1 item
                    0 -> {
                        val firstItemHeight = itemHeight * GalleryGridDefaults.FirstItemSpanCount
                        rowHeight = firstItemHeight
                        height += rowHeight
                        measurable.measure(
                            Constraints(
                                minWidth = firstItemWidth,
                                maxWidth = firstItemWidth,
                                minHeight = firstItemHeight,
                                maxHeight = firstItemHeight
                            )
                        ) to IntOffset(
                            x = start,
                            y = top
                        )
                    }
                    // 1 row, 2 item
                    1 -> {
                        measurable.measure(
                            Constraints(
                                minWidth = itemWidth,
                                maxWidth = itemWidth,
                                minHeight = itemHeight,
                                maxHeight = itemHeight,
                            )
                        ) to IntOffset(
                            x = start + firstItemWidth,
                            y = top
                        )
                    }
                    // 1 row, 3 item
                    2 -> {
                        measurable.measure(
                            Constraints(
                                minWidth = itemWidth,
                                maxWidth = itemWidth,
                                minHeight = itemHeight,
                                maxHeight = itemHeight
                            )
                        ) to IntOffset(
                            x = start + firstItemWidth,
                            y = top + itemHeight
                        )
                    }
                    // others
                    else -> {
                        val column = index % GalleryGridDefaults.SpanCount
                        if (column == 0) {
                            rowHeight = itemHeight
                            height += rowHeight
                        }
                        measurable.measure(
                            Constraints(
                                minWidth = itemWidth,
                                maxWidth = itemWidth,
                                minHeight = itemHeight,
                                maxHeight = itemHeight
                            )
                        ) to IntOffset(
                            x = start + column * itemWidth,
                            y = height - rowHeight
                        )
                    }
                }
            }

        return layout(width, height + bottom) {
            placeablesWithOffset.forEach { (placeable, offset) ->
                placeable.place(offset)
            }
        }
    }
}

private object GalleryGridDefaults {
    const val SpanCount = 3
    const val MaxPhotoCount = 9
    val AspectRatio = 1f
    const val FirstItemSpanCount = 2
}

@Preview
@Composable
private fun StyleContentPreview1() {
    Surface {
        StyleContent(
            listOf(
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                Style(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
            )
        )
    }

}
