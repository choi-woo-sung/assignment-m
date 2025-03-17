package com.woosung.compose.data.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    @Provides
    @Singleton
    fun provideOkHttpClient(
    ): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
//        .addInterceptor(TokenInterceptor())
        .build()


    @Provides
    @Singleton
    fun provideRetrofitBuilder(
        okHttpClient: OkHttpClient,
    ): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(
            Json() {
                ignoreUnknownKeys = true
                coerceInputValues = true
            }.asConverterFactory("application/json".toMediaType()),
        )
        .baseUrl("https://recruit.heydealer.com")
        .build()


    @Singleton
    @Provides
    fun provideHeyApi(
        retrofit: Retrofit,
    ): HeyApi = retrofit.create(HeyApi::class.java)

}