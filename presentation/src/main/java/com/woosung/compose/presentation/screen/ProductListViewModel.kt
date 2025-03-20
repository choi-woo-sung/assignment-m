package com.woosung.compose.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woosung.compose.domain.model.Product
import com.woosung.compose.domain.repository.api.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<ProductListUiState> =
        MutableStateFlow(ProductListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getProducts()
    }


    fun getProducts() {
        viewModelScope.launch {
            _uiState.emit(ProductListUiState.Loading)
            productRepository.getProducts().onSuccess {
                _uiState.emit(ProductListUiState.Success(it))
            }.onFailure {
                _uiState.emit(ProductListUiState.Error(it.message ?: "Unknown Error"))
            }
        }
    }


    sealed class ProductListUiState {
        data object Loading : ProductListUiState()
        data class Success(val productList: List<Product>) : ProductListUiState()
        data class Error(val message: String) : ProductListUiState()
    }
}

