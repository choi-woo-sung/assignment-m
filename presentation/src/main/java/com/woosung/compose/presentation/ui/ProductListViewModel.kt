package com.woosung.compose.presentation.ui

import androidx.compose.runtime.Stable
import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.woosung.compose.domain.model.Good
import com.woosung.compose.domain.repository.api.ProductRepository
import com.woosung.compose.presentation.model.ContentUI
import com.woosung.compose.presentation.model.ProductUi
import com.woosung.compose.presentation.model.toUiModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class ProductListViewModel @AssistedInject constructor(
    @Assisted initialState: ProductListUiState,
    private val productRepository: ProductRepository
) : MavericksViewModel<ProductListUiState>(initialState) {

    init {
        getProducts()
    }

    private val _sideEffects = MutableSharedFlow<ProductListSideEffect>()
    val sideEffect = _sideEffects.asSharedFlow()


    fun getProducts() {
        viewModelScope.launch {
            setState { copy(isLoading = true) }
            productRepository.getProducts().onSuccess {
                val productListUiModel = it.toUiModel()
                setState { copy(isLoading = false, productList = productListUiModel) }
            }.onFailure {
                setState { copy(isError = true, isLoading = false) }
            }
        }
    }

    /**
     *  새로운 상품을 추천받는다.
     *  내부적으로는 셔플을 사용한다.
     */
    fun shuffleProductList(uuid: String) {
        withState { currentUiState ->
            val currentProductList = currentUiState.productList.toMutableList()
            val indexOf = currentProductList.indexOfFirst { it.uuid == uuid }

            //값을 찾았다면
            if (indexOf != -1) {
                //셔플된 리스트
                val shuffleList = currentProductList[indexOf].contents.let { content ->
                    when (content) {
                        is ContentUI.BannerContent -> content.copy(bannerUi = content.bannerUi.immutableShuffle())
                        is ContentUI.GridContent -> content.copy(goodUi = content.goodUi.immutableShuffle())
                        is ContentUI.ScrollContent -> content.copy(goodUi = content.goodUi.immutableShuffle())
                        is ContentUI.StyleContent -> content.copy(styleUi = content.styleUi.immutableShuffle())
                        ContentUI.Unknown -> content
                    }
                }
                currentProductList[indexOf] =
                    currentProductList[indexOf].copy(contents = shuffleList)
            }
            setState {
                copy(productList = currentProductList.toPersistentList())
            }
        }
    }


    fun loadMoreProduct(uuid: String) {
        withState { currentUiState ->
            val currentProductList = currentUiState.productList.toMutableList()
            val indexOf = currentProductList.indexOfFirst { it.uuid == uuid }

            //값을 찾았다면
            if (indexOf != -1) {

                //합쳐진 리스트
                val totalProductList = currentProductList[indexOf].contents.let { content ->
                    when (content) {
                        is ContentUI.BannerContent -> content
                        is ContentUI.GridContent -> {
                            val newGoodsUIList = content.goodUi.toMutableList() + content.extraList[0]
                            content.copy(
                                goodUi = newGoodsUIList.toImmutableList(),
                                extraList = content.extraList.drop(1).toImmutableList(),
                                footer =  if(content.isMore) content.footer else null
                            )

                        }

                        is ContentUI.ScrollContent -> content
                        is ContentUI.StyleContent -> {
                            val newStyleUiList = content.styleUi.toMutableList()
                                .apply { addAll(content.extraList[0]) }
                            content.copy(
                                styleUi = newStyleUiList.toImmutableList(),
                                extraList = content.extraList.drop(1).toImmutableList(),
                                footer =  if(content.isMore) content.footer else null
                            )
                        }

                        ContentUI.Unknown -> content
                    }
                }
                currentProductList[indexOf] = currentProductList[indexOf].copy(contents = totalProductList, )
            }
            setState {
                copy(productList = currentProductList.toPersistentList())
            }
        }
    }

    fun navigateProductDetail(linkUrl: String) = viewModelScope.launch {
        _sideEffects.emit(ProductListSideEffect.NavigateToWebSite(linkUrl))
    }


    private fun <T> List<T>.immutableShuffle(): ImmutableList<T> {
        return this.shuffled().toPersistentList()
    }

//    private fun ContentUI..updateGridItems(): ContentUI. {
//        val newGoodsUIList = goodUi.toMutableList().apply { addAll(extraList[0]) }
//        return copy(
//            goodUi = newGoodsUIList.toImmutableList(),
//            extraList = extraList.drop(1).toImmutableList(),
//        )
//    }

//    private fun GoodsUI.Style.updateStyleItems(): GoodsUI.Style {
//        val newStyleUIList = styleUi.toMutableList().apply { addAll(extraList[0]) }
//        return copy(
//            styleUi = newStyleUIList.toImmutableList(),
//            extraList = extraList.drop(1).toImmutableList(),
//        )
//    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<ProductListViewModel, ProductListUiState> {
        override fun create(state: ProductListUiState): ProductListViewModel
    }

    companion object :
        MavericksViewModelFactory<ProductListViewModel, ProductListUiState> by hiltMavericksViewModelFactory()
}

@Stable
data class ProductListUiState(
    val productList: ImmutableList<ProductUi> = persistentListOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
) : MavericksState


@Stable
sealed class ProductListSideEffect {
    data class NavigateToWebSite(val linkUrl: String) : ProductListSideEffect()
}
