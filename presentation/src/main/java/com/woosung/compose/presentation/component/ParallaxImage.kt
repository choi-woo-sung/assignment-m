package com.woosung.compose.presentation.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.fontscaling.MathUtils.lerp
import androidx.compose.ui.unit.toIntSize

@SuppressLint("RestrictedApi")
@Composable
fun ParallaxImage(imageBitmap: ImageBitmap, currentPageOffset: Float) {
    val drawSize = IntSize(imageBitmap.width, imageBitmap.height)
    val screenWidth = LocalConfiguration.current.screenWidthDp
    // Calculate parallax offset
    val parallaxOffset = currentPageOffset * screenWidth * 2f

    Canvas(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer {
                translationX = lerp(10f, 0f, 1f - currentPageOffset)
            } // Apply translation
    ) {
        translate(left = parallaxOffset) {
            drawImage(
                image = imageBitmap,
                srcSize = drawSize,
                dstSize = size.toIntSize(),
            )
        }
    }
}
