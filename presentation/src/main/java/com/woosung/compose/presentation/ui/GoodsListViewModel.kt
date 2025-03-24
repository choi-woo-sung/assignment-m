package com.woosung.compose.presentation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woosung.compose.domain.repository.api.GoodsRepository
import com.woosung.compose.presentation.model.ContentUI
import com.woosung.compose.presentation.model.toUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.runningFold
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GoodsListViewModel @Inject constructor(
    private val goodsRepository: GoodsRepository
) : ViewModel() {

    private val events = Channel<GoodsListEvent>()

    val uiState = events.receiveAsFlow().runningFold(GoodsListUiState(), ::reduceState)
        .stateIn(viewModelScope, SharingStarted.Lazily, GoodsListUiState())

    private fun reduceState(current: GoodsListUiState, event: GoodsListEvent): GoodsListUiState {
        return when (event) {
            GoodsListEvent.Loading -> current.copy(isLoading = true, isError = false)
            GoodsListEvent.Error -> current.copy(isError = true, isLoading = false)

            is GoodsListEvent.Loaded -> current.copy(
                isLoading = false,
                contentsList = event.contentList.toImmutableList(),
                isError = false
            )

            is GoodsListEvent.Shuffle -> current.copy(contentsList = event.contentList.toImmutableList())
        }
    }

    private val _sideEffects = MutableSharedFlow<GoodsListSideEffect>()
    val sideEffect = _sideEffects.asSharedFlow()

    init {
        goodsRepository.goodsListFlow.onEach {
            events.send(GoodsListEvent.Loaded(it.map { it.toUiModel() }))
        }.launchIn(viewModelScope)
        fetchGoods()
    }


    fun fetchGoods() = viewModelScope.launch {
        events.send(GoodsListEvent.Loading)
        runCatching {
            goodsRepository.getGoods()
        }.onFailure {
            events.send(GoodsListEvent.Error)
        }
    }

    /**
     *  새로운 상품을 추천받는다.
     *  내부적으로는 셔플을 사용한다.
     */
    fun shuffleGoodsList(uuid: String) = viewModelScope.launch {
        val currentGoodsList = uiState.value.contentsList.toMutableList()
        val indexOf = currentGoodsList.indexOfFirst { it.uuid == uuid }

        //값을 찾았다면
        if (indexOf != -1) {
            //셔플된 리스트
            val shuffleList = currentGoodsList[indexOf].let { content ->
                when (content) {
                    is ContentUI.BannerContent -> content.copy(bannerUi = content.bannerUi.immutableShuffle())
                    is ContentUI.GridContent -> content.copy(goodUi = content.goodUi.immutableShuffle())
                    is ContentUI.ScrollContent -> content.copy(goodUi = content.goodUi.immutableShuffle())
                    is ContentUI.StyleContent -> content.copy(styleUi = content.styleUi.immutableShuffle())
                    ContentUI.Unknown -> content
                }
            }
            currentGoodsList[indexOf] = shuffleList
        }
        events.send(GoodsListEvent.Shuffle(currentGoodsList))
    }


    fun loadMoreGoods(uuid: String) = viewModelScope.launch {
        val contentType =
            uiState.value.contentsList.find { it.uuid == uuid }?.contentType ?: return@launch

        goodsRepository.appendGoods(contentType)
    }

    fun navigateGoodsDetail(linkUrl: String) = viewModelScope.launch {
        _sideEffects.emit(GoodsListSideEffect.NavigateToWebSite(linkUrl))
    }


    private fun <T> List<T>.immutableShuffle(): ImmutableList<T> {
        return this.shuffled().toPersistentList()
    }

    fun executeUnknown() = viewModelScope.launch {
        _sideEffects.emit(GoodsListSideEffect.ToastMessage("현재 버전으로는 실행할수 없는 기능입니다. 업데이트를 해주세요."))
    }

}

data class GoodsListUiState(
    val contentsList: ImmutableList<ContentUI> = persistentListOf(),
    val isLoading: Boolean = false,
    val isError: Boolean = false
)


internal sealed class GoodsListEvent {
    data object Loading : GoodsListEvent()
    data class Loaded(val contentList: List<ContentUI>) : GoodsListEvent()
    data class Shuffle(val contentList: List<ContentUI>) : GoodsListEvent()

    data object Error : GoodsListEvent()
}


sealed class GoodsListSideEffect {
    data class NavigateToWebSite(val linkUrl: String) : GoodsListSideEffect()
    data class ToastMessage(val message: String) : GoodsListSideEffect()
}
