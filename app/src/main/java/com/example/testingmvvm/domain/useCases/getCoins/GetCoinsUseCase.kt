package com.example.testingmvvm.domain.useCases.getCoins

import android.util.Log
import com.example.testingmvvm.common.Resources
import com.example.testingmvvm.data.remote.dtoModel.coinsDto.CoinsDto
import com.example.testingmvvm.data.remote.dtoModel.coinsDto.toCoin
import com.example.testingmvvm.domain.model.Coin
import com.example.testingmvvm.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend fun invoke() = repository.getCoins()
}