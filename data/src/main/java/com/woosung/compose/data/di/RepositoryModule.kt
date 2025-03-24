package com.woosung.compose.data.di

import com.woosung.compose.data.repository.impl.GoodsRepositoryImp
import com.woosung.compose.domain.repository.api.GoodsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(ViewModelComponent::class)
internal interface RepositoryModule {

     @Binds
     @ViewModelScoped
     fun bindGoodsRepository(
         goodsRepositoryImp: GoodsRepositoryImp
     ): GoodsRepository
}
