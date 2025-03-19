//package com.woosung.compose.presentation.component.content
//
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxHeight
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.GridItemSpan
//import androidx.compose.foundation.lazy.grid.LazyGridScope
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
//import com.woosung.compose.domain.model.Style
//import com.woosung.compose.presentation.R
//import com.woosung.compose.presentation.component.ProductItem
//import com.woosung.compose.presentation.util.debugPlaceholder
//
//
//internal fun LazyGridScope.StyleContent(styleList: List<Style>) {
//    styleList.forEachIndexed { index, style ->
//        if (index == 0) {
//            item(span = { GridItemSpan(2) }) {
//                AsyncImage(
//                    modifier = Modifier.fillMaxWidth().aspectRatio(1f),
//                    model = style.thumbnailURL,
//                    contentDescription = "",
//                    placeholder = debugPlaceholder(
//                        R.drawable.test
//                    ),
//                    contentScale = ContentScale.Crop
//                )
//            }
//        } else {
//            item {
//                AsyncImage(
//                    model = style.thumbnailURL,
//                    contentDescription = "",
//                    placeholder = debugPlaceholder(
//                        R.drawable.test
//                    )
//                )
//            }
//        }
//    }
//}
//
//
////@Composable
////private fun LazyGridScope.StyleItem(model: Any) {
////    item(span = GridCells.Fixed(2)) {
////        AsyncImage(
////            model = model,
////        )
////    }
////}
//
//
//@Preview
//@Composable
//private fun StyleContentPreview() {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//    ) {
//        StyleContent(
//            styleList = listOf(
//                Style(
//                    linkURL = "",
//                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
//                ),
//                Style(
//                    linkURL = "",
//                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
//                ),
//                Style(
//                    linkURL = "",
//                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
//                ),
//                Style(
//                    linkURL = "",
//                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
//                ),
//                Style(
//                    linkURL = "",
//                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
//                ),
//                Style(
//                    linkURL = "",
//                    thumbnailURL = "https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png",
//                ),
//            )
//        )
//    }
//}
//
//@Preview
//@Composable
//private fun StyleContentPreview123() {
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(3),
//    ) {
//        (0..6).forEachIndexed { index, it ->
//            if(index == 0){
//                item(span = { GridItemSpan(2) }) {
//                    Button(onClick = {}) {
//                        Text("Button $it")
//                    }
//                }
//            }
//            item {
//                Button(onClick = {}) {
//                    Text("Button $it")
//                }
//            }
//        }
//    }
//}
