package com.example.testingmvvm.app.coinDetail.presentation

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingmvvm.app.ViewState
import com.example.testingmvvm.common.Resources
import com.example.testingmvvm.domain.model.CoinDetails
import com.example.testingmvvm.domain.useCases.getCoin.GetCoinUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CoinDetailsViewModel  @Inject constructor(
    private val getCoinUseCase: GetCoinUseCase
): ViewModel(), LifecycleObserver {
    val viewState = MutableLiveData<ViewState<CoinDetails?>>()

    fun fetchDetails(id : String) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            getCoinUseCase.invoke(id).onEach { result ->
                when(result){
                    is Resources.Success -> {
                        viewState.value = ViewState.success(result.data)
                    }
                    is Resources.Error -> {
                        viewState.value = ViewState.error(result.message)
                    }
                    is Resources.Loading -> {
                        viewState.value = ViewState.loading()
                    }
                }
            }
        }
    }
}