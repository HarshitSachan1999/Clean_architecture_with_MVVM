package com.example.testingmvvm.domain.model

import com.example.testingmvvm.data.remote.dtoModel.coinDetailsDto.*

data class CoinDetails(
    val description: String,
    val id: String,
    val is_active: Boolean,
    val message: String,
    val name: String,
    val symbol: String,
    val tags: List<String>,
    val team: List<TeamMembers>
)
