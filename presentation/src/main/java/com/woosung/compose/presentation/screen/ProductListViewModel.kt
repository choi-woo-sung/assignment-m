package com.woosung.compose.presentation.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.woosung.compose.domain.model.Product
import com.woosung.compose.domain.repository.api.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {


    init {
        getProducts()
    }

    private val _uiState = MutableStateFlow(ProductListUiState())
    val uiState = _uiState.asStateFlow()


    fun getProducts() {
        viewModelScope.launch {
            val result = productRepository.getProducts()
            _uiState.value = ProductListUiState(productList = result)

        }
    }


}


data class ProductListUiState(
    val productList: List<Product> = emptyList(),
)
