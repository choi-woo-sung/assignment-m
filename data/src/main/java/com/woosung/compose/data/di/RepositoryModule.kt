package com.woosung.compose.data.di

import com.woosung.compose.data.repository.impl.GoodsRepositoryImp
import com.woosung.compose.domain.repository.api.GoodsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface RepositoryModule {

     @Binds
     @Singleton
     fun bindGoodsRepository(
         goodsRepositoryImp: GoodsRepositoryImp
     ): GoodsRepository
}
