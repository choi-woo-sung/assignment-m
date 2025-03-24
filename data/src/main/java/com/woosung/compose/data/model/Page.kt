package com.woosung.compose.data.model

internal data class Page(
    val initialSize:Int,
    val chunkSize:Int,
    val endOfContent:Boolean
)
