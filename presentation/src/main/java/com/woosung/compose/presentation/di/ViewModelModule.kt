package com.woosung.compose.presentation.di

import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.MavericksViewModelComponent
import com.airbnb.mvrx.hilt.ViewModelKey
import com.woosung.compose.presentation.ui.ProductListViewModel
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.multibindings.IntoMap

@Module
@InstallIn(MavericksViewModelComponent::class)
interface ExampleViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProductListViewModel::class)
    fun exampleViewModelFactory(factory: ProductListViewModel.Factory): AssistedViewModelFactory<*, *>
}
