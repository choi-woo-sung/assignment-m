package com.woosung.compose.presentation.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.compose.rememberConstraintsSizeResolver
import com.woosung.compose.presentation.R
import com.woosung.compose.presentation.util.debugPlaceholder


@Composable
fun NetworkImage(
    modifier: Modifier = Modifier,
    url: String,
    contentDescription: String,
    debugPlaceholder: Int = R.drawable.test
) {



    val sizeResolver = rememberConstraintsSizeResolver()

    val painter = rememberAsyncImagePainter(
        model = url,
        placeholder = debugPlaceholder(debugPlaceholder)
    )

    val state =  painter.state.collectAsState().value

    Box(Modifier){

        when (state) {
            is AsyncImagePainter.State.Loading -> {
                Box(Modifier.size(100.dp)){
                CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

            is AsyncImagePainter.State.Error -> {
                Log.e("NetworkImage", "Error: ${state.result.throwable}")
            }

            is AsyncImagePainter.State.Success -> {
                Image(
                    modifier = Modifier.then(sizeResolver).fillMaxWidth(),
                    painter = painter,
                    contentDescription = contentDescription,
                    contentScale = ContentScale.FillBounds
                )
            }

            else -> {

            }
        }
    }
}
