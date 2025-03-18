package com.woosung.compose.data.di

import com.woosung.compose.data.remote.api.ProductRemoteSource
import com.woosung.compose.data.remote.impl.ProductRemoteSourceImp
import com.woosung.compose.data.repository.impl.ProductRepositoryImp
import com.woosung.compose.domain.repository.api.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface DataSourceModule {
    @Binds
    @Singleton
    fun bindProductRemoteSource(
        productRemoteSourceImp: ProductRemoteSourceImp
    ): ProductRemoteSource
}
