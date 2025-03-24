package com.woosung.compose.presentation.component.content

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.dp
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.component.NetworkImage
import com.woosung.compose.presentation.model.StyleUi
import com.woosung.compose.presentation.theme.MsPadding
import kotlin.math.roundToInt

@Composable
fun StyleContent(
    styleList: List<StyleUi>,
    onClicked: (String) -> Unit = {}
) {
    StyleGrid(
        contentPadding = PaddingValues(horizontal = 4.dp),
    ) {
        styleList.forEach { style ->
            Box(Modifier.clickable {
                onClicked(style.linkURL)
            }) {
                NetworkImage(
                    modifier = Modifier.fillMaxSize(),
                    url = style.thumbnailURL,
                    contentDescription = "",
                    debugPlaceholder =  R.drawable.test
                )
            }

        }
    }
}

@Composable
fun StyleGrid(
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
    StyleGridMeasurePolicy(contentPadding)
}

private class StyleGridMeasurePolicy(
    private val contentPadding: PaddingValues,
) : MeasurePolicy {

    override fun MeasureScope.measure(
        measurables: List<Measurable>,
        constraints: Constraints
    ): MeasureResult {
        val start = contentPadding.calculateLeftPadding(LayoutDirection.Ltr).roundToPx()
        val top = contentPadding.calculateTopPadding().roundToPx()
        val end = contentPadding.calculateRightPadding(LayoutDirection.Ltr).roundToPx()
        val bottom = contentPadding.calculateBottomPadding().roundToPx()

        val horizontalSpacing = MsPadding.small.roundToPx()
        val verticalSpacing = MsPadding.small.roundToPx()

        val width = constraints.maxWidth
        val totalHorizontalSpacing =
            horizontalSpacing * (StyleGridDefaults.SpanCount - 1)
        val itemWidth = ((width - start - end - totalHorizontalSpacing) / StyleGridDefaults.SpanCount)
        val firstItemWidth = itemWidth * StyleGridDefaults.FirstItemSpanCount +
                horizontalSpacing * (StyleGridDefaults.FirstItemSpanCount - 1)

        var height = top
        var rowHeight = 0
        val itemHeight = (itemWidth / StyleGridDefaults.AspectRatio).roundToInt()

        val placeablesWithOffset = measurables.mapIndexed { index, measurable ->
            when (index) {
                0 -> {
                    val firstItemHeight = itemHeight * StyleGridDefaults.FirstItemSpanCount +
                            verticalSpacing * (StyleGridDefaults.FirstItemSpanCount - 1)
                    rowHeight = firstItemHeight
                    height += rowHeight + verticalSpacing
                    measurable.measure(
                        Constraints.fixed(firstItemWidth, firstItemHeight)
                    ) to IntOffset(
                        x = start,
                        y = top
                    )
                }

                1 -> {
                    measurable.measure(
                        Constraints.fixed(itemWidth, itemHeight)
                    ) to IntOffset(
                        x = start + firstItemWidth + horizontalSpacing,
                        y = top
                    )
                }

                2 -> {
                    measurable.measure(
                        Constraints.fixed(itemWidth, itemHeight)
                    ) to IntOffset(
                        x = start + firstItemWidth + horizontalSpacing,
                        y = top + itemHeight + verticalSpacing
                    )
                }

                else -> {
                    val gridIndex = index - 3
                    val column = gridIndex % StyleGridDefaults.SpanCount
                    val row = gridIndex / StyleGridDefaults.SpanCount

                    val x = start + column * (itemWidth + horizontalSpacing)
                    val y = height + row * (itemHeight + verticalSpacing)

                    measurable.measure(
                        Constraints.fixed(itemWidth, itemHeight)
                    ) to IntOffset(x, y)
                }
            }
        }

        // 마지막 줄까지 높이 반영
        val totalGridRows = ((measurables.size - 3).coerceAtLeast(0) + StyleGridDefaults.SpanCount - 1) /
                StyleGridDefaults.SpanCount
        val additionalHeight =
            totalGridRows * itemHeight + (totalGridRows - 1).coerceAtLeast(0) * verticalSpacing

        return layout(width, height + additionalHeight + bottom) {
            placeablesWithOffset.forEach { (placeable, offset) ->
                placeable.place(offset)
            }
        }
    }
}

private object StyleGridDefaults {
    const val SpanCount = 3
    val AspectRatio = 1f
    const val FirstItemSpanCount = 2
}

@Preview
@Composable
private fun StyleContentPreview() {
    Surface {
        StyleContent(
            listOf(
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
                StyleUi(
                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
                    linkURL = ""
                ),
            )
        )
    }

}
