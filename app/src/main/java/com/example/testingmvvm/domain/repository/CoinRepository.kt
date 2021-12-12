package com.example.testingmvvm.domain.repository

import com.example.testingmvvm.common.Resources
import com.example.testingmvvm.data.remote.dtoModel.coinsDto.CoinsDto
import com.example.testingmvvm.domain.model.Coin
import com.example.testingmvvm.domain.model.CoinDetails
import kotlinx.coroutines.flow.Flow

interface CoinRepository {

    suspend fun getCoins() : Flow<Resources<List<Coin>>>

    suspend fun getCoinDetails(id:String) : Flow<Resources<CoinDetails>>
}