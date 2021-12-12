package com.example.testingmvvm.data.remote.dtoModel.coinsDto

import com.example.testingmvvm.domain.model.Coin

data class CoinsDto(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Int,
    val symbol: String,
    val type: String
)

fun CoinsDto.toCoin():Coin{
    return Coin(
        id = id,
        is_active = is_active,
        name = name,
        rank = rank,
        symbol = symbol
    )
}