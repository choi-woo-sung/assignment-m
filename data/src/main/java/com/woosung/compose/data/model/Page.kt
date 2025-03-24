package com.woosung.compose.data.model

data class Page(
    val initialSize:Int,
    val chunkSize:Int,
    val endOfContent:Boolean
)
