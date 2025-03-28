package com.woosung.compose.network.api

import com.woosung.compose.data.ApiAbstract
import com.woosung.compose.data.handle.NetworkResult
import com.woosung.compose.data.handle.handleApi
import com.woosung.compose.data.model.HeaderResponse
import com.woosung.compose.data.network.AssignmentApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class MusinsaApiTest : ApiAbstract<AssignmentApi>() {
    private lateinit var service: AssignmentApi

    @BeforeEach
    fun initService() {
        service = createService(AssignmentApi::class.java)
    }

    @Nested
    @DisplayName("MusinsaAPI  Goods 데이터가 200 떨어졌을때")
    inner class LoginDataSuccess {
        @BeforeEach
        fun setup() {
            enqueueResponse("/list.json")
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("데이터 파싱과 응답코드가 정상적으로 반환")
        fun verifyGetGoods() = runTest {
            val response = service.getGoodsList()
            Assertions.assertTrue(response.code() == 200)
            val parsingData = handleApi { response }

            when (parsingData) {
                is NetworkResult.Success -> {
                    Assertions.assertEquals(
                        parsingData.data[0].header,
                        HeaderResponse(
                            title = "클리어런스",
                        ),
                    )
                }

                else -> {
                    Assertions.fail("예상하지 못한 결과")
                }
            }
        }
    }
}
