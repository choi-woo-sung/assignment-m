package com.woosung.compose.data.repository

import app.cash.turbine.test
import com.woosung.compose.data.fake.FailedNewTypeGoodsRemoteSource
import com.woosung.compose.data.fake.SuccessBannerGoodsRemoteSource
import com.woosung.compose.data.fake.SuccessStyleGoodsRemoteSource
import com.woosung.compose.data.repository.impl.GoodsRepositoryImp
import com.woosung.compose.domain.model.Content
import com.woosung.compose.domain.model.ContentType
import com.woosung.compose.domain.model.FooterType
import com.woosung.compose.domain.repository.api.GoodsRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class GoodsRepositoryTest {

    private lateinit var goodsRepository: GoodsRepository

    @Nested
    @DisplayName("상품 리스트 Style이 정상적으로 호출되었을때")
    inner class StyleSuccess {
        @BeforeEach
        fun initService() {
            goodsRepository = GoodsRepositoryImp(SuccessStyleGoodsRemoteSource())
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("Style이 잘 파싱되는가?")
        fun verifyGetGoodsList() = runTest {
            var currentGoodsList = goodsRepository.getGoods()


            goodsRepository.goodsListFlow.test {
                val goodsList = awaitItem()

                Assertions.assertTrue(goodsList[0].contents is Content.StyleType)


            }

        }


        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("Style의 초기데이터가 Paging 된 6개인가?")
        fun verifyGetGoodsList2() = runTest {
            goodsRepository.getGoods()


            goodsRepository.goodsListFlow.test {
                val goodsList = awaitItem()

                if (goodsList[0].contents is Content.StyleType) {
                    val styleList = (goodsList[0].contents as Content.StyleType).data
                    Assertions.assertTrue(styleList.size == 6)
                }


            }

        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("Style의 데이터가 append()를 호출하면 9개인가?")
        fun verifyGetGoodsList3() = runTest {
            goodsRepository.getGoods()

            goodsRepository.appendGoods(ContentType.Style)

            goodsRepository.goodsListFlow.test {
                val goodsList = awaitItem()

                if (goodsList[0].contents is Content.StyleType) {
                    val styleList = (goodsList[0].contents as Content.StyleType).data
                    Assertions.assertTrue(styleList.size == 9)
                }
            }
        }
    }

    @Nested
    @DisplayName("상품 리스트 Banner이 정상적으로 호출되었을때")
    inner class BannerSuccess {
        @BeforeEach
        fun initService() {
            goodsRepository = GoodsRepositoryImp(SuccessBannerGoodsRemoteSource())
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("Banner이 잘 파싱되는가?")
        fun verifyGetGoodsList() = runTest {
            var currentGoodsList = goodsRepository.getGoods()


            goodsRepository.goodsListFlow.test {
                val goodsList = awaitItem()

                Assertions.assertTrue(goodsList[0].contents is Content.BannerType)

            }

        }


        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("Banner의 초기데이터가 Paging 된 6개인가?")
        fun verifyGetGoodsList2() = runTest {
            goodsRepository.getGoods()


            goodsRepository.goodsListFlow.test {
                val goodsList = awaitItem()

                if (goodsList[0].contents is Content.BannerType) {
                    val styleList = (goodsList[0].contents as Content.BannerType).data
                    Assertions.assertTrue(styleList.size == 5)
                }


            }

        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("Style의 데이터가 append()를 호출하면 9개인가?")
        fun verifyGetGoodsList3() = runTest {
            goodsRepository.getGoods()

            goodsRepository.appendGoods(ContentType.Style)

            goodsRepository.goodsListFlow.test {
                val goodsList = awaitItem()

                if (goodsList[0].contents is Content.StyleType) {
                    val styleList = (goodsList[0].contents as Content.StyleType).data
                    Assertions.assertTrue(styleList.size == 9)
                }
            }
        }
    }


    @Nested
    @DisplayName("상품데이터에 신규타입들이 들어왔을때")
    inner class NewTypeFailed {
        @BeforeEach
        fun initService() {
            goodsRepository = GoodsRepositoryImp(FailedNewTypeGoodsRemoteSource())
        }

        @OptIn(ExperimentalCoroutinesApi::class)
        @Test
        @DisplayName("타입은 Unknown으로 들어왔는가?")
        fun verifyGetGoodsList2() = runTest {
            goodsRepository.getGoods()


            goodsRepository.goodsListFlow.test {
                val goodsList = awaitItem()

                Assertions.assertTrue(goodsList[0].contents is Content.Unknown)
                Assertions.assertTrue(goodsList[0].footer?.type == FooterType.UNKNOWN)
            }
        }
    }




}
