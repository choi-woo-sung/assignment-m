package com.woosung.compose.presentation.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import coil.ImageLoader
import coil.request.ErrorResult
import coil.request.ImageRequest
import coil.request.SuccessResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object ImageUtil {
    fun loadImageBitmap(
        context: Context,
        scope: CoroutineScope,
        imageUrl: String,
        onBitmapLoaded: (Bitmap) -> Unit
    ) {
        scope.launch(Dispatchers.IO) {
            val loader = ImageLoader(context)
            val request = ImageRequest.Builder(context)
                .data(imageUrl)
                .allowHardware(false)
                .build()

            when (val result = loader.execute(request)) {
                is SuccessResult -> {
                    (result.drawable as? BitmapDrawable)?.bitmap?.let {
                        onBitmapLoaded(it)
                    }
                }

                is ErrorResult -> Log.e("TAG", "Error loading image: ${result.throwable}")
            }
        }
    }
}
