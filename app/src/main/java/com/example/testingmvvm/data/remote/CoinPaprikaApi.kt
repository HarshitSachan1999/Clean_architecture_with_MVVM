package com.example.testingmvvm.data.remote

import com.example.testingmvvm.data.remote.dtoModel.coinDetailsDto.CoinDetailsDto
import com.example.testingmvvm.data.remote.dtoModel.coinsDto.CoinsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {

    @GET("/v1/coins")
    suspend fun getCoins() : List<CoinsDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinDetails(@Path("coinId")coinId : String) : CoinDetailsDto

}