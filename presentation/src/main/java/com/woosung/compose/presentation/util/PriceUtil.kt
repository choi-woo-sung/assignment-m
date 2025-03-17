package com.woosung.compose.presentation.util

object PriceUtil {
    /**
     * 3자리 마다 ,를 추가하는 함수
     *
     * @param number
     * @return
     */
    fun addCommas(number: Number): String {
        return number.toString().reversed()
            .chunked(3)
            .joinToString(",")
            .reversed()
    }
}
