package com.example.testingmvvm.di

import com.example.testingmvvm.common.Constants
import com.example.testingmvvm.data.remote.CoinPaprikaApi
import com.example.testingmvvm.data.repository.CoinRepositoryImpl
import com.example.testingmvvm.domain.repository.CoinRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)  //Live as long as application
object AppModule {
    @Provides
    @Singleton
    fun providePaprikaApi(): CoinPaprikaApi{
        return Retrofit.Builder()
            .baseUrl(Constants.coinPaprikaAPI)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CoinPaprikaApi::class.java)

    }

    @Provides
    @Singleton
    fun provideCoinRepository(api : CoinPaprikaApi):CoinRepository{
        return CoinRepositoryImpl(api)
    }
}