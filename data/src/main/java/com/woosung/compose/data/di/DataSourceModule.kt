package com.woosung.compose.data.di

import com.woosung.compose.data.remote.api.GoodsRemoteSource
import com.woosung.compose.data.remote.impl.GoodsRemoteSourceImp
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
    fun bindGoodsRemoteSource(
        goodsRemoteSourceImp: GoodsRemoteSourceImp
    ): GoodsRemoteSource
}
