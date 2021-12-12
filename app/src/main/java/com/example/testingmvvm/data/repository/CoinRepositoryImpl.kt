package com.example.testingmvvm.data.repository

import com.example.testingmvvm.common.Resources
import com.example.testingmvvm.data.remote.CoinPaprikaApi
import com.example.testingmvvm.data.remote.dtoModel.coinDetailsDto.CoinDetailsDto
import com.example.testingmvvm.data.remote.dtoModel.coinDetailsDto.toCoinDetail
import com.example.testingmvvm.data.remote.dtoModel.coinsDto.CoinsDto
import com.example.testingmvvm.data.remote.dtoModel.coinsDto.toCoin
import com.example.testingmvvm.domain.model.Coin
import com.example.testingmvvm.domain.model.CoinDetails
import com.example.testingmvvm.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api : CoinPaprikaApi
) : CoinRepository {

    override suspend fun getCoins(): Flow<Resources<List<Coin>>> = flow {
        try {
            emit(Resources.Loading<List<Coin>>())
            val coins = api.getCoins().map { it.toCoin() }
            emit(Resources.Success<List<Coin>>(coins))
        }catch (e:Exception){
            emit(Resources.Error<List<Coin>>(e.localizedMessage?: "Unable to parse data"))
        }

    }

    override suspend fun getCoinDetails(id: String): Flow<Resources<CoinDetails>> = flow {
        try {
            emit(Resources.Loading<CoinDetails>())
            val coin = api.getCoinDetails(id).toCoinDetail()
            emit(Resources.Success<CoinDetails>(coin))
        }catch (e:Exception){
            emit(Resources.Error<CoinDetails>(e.localizedMessage ?: "Unable to parse data"))
        }
    }


}