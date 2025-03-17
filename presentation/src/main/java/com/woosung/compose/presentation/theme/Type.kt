package com.woosung.compose.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.woosung.compose.presentation.R


val SpoqaHansFontFamily = FontFamily(
    Font(R.font.spoqahansansneo_light, FontWeight.Light),
    Font(R.font.spoqahansansneo_regular, FontWeight.Normal),
    Font(R.font.spoqahansansneo_bold, FontWeight.Bold),
    Font(R.font.spoqahansansneo_thin, FontWeight.Thin),
    Font(R.font.spoqahansansneo_medium, FontWeight.Medium),
)

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
    ,
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
)

val Headline3 = TextStyle(
    fontFamily = SpoqaHansFontFamily,
    fontWeight = FontWeight.W700,
    fontSize = 24.sp,
    lineHeight = 40.sp,
    letterSpacing = 0.sp
)

val Body1 = TextStyle(
    fontFamily = SpoqaHansFontFamily,
    fontWeight = FontWeight.W400,
    fontSize = 16.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)


val SubTitle2 = TextStyle(
    fontFamily = SpoqaHansFontFamily,
    fontWeight = FontWeight.W700,
    fontSize = 16.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp
)

val SubTitle3 = TextStyle(
    fontFamily = SpoqaHansFontFamily,
    fontWeight = FontWeight.W700,
    fontSize = 14.sp,
    lineHeight = 24.sp,
    letterSpacing = 0.sp
)

val Caption1 = TextStyle(
    fontFamily = SpoqaHansFontFamily,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp
)

val Form3B = TextStyle(
    fontFamily = SpoqaHansFontFamily,
    fontWeight = FontWeight.W700,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
)

val Form3 = TextStyle(
    fontFamily = SpoqaHansFontFamily,
    fontWeight = FontWeight.W400,
    fontSize = 12.sp,
    lineHeight = 16.sp,
    letterSpacing = 0.sp
)