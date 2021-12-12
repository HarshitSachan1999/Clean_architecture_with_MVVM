package com.example.testingmvvm.domain.useCases.getCoin

import com.example.testingmvvm.domain.repository.CoinRepository
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {
    suspend fun invoke(id:String) = repository.getCoinDetails(id)
}