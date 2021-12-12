package com.example.testingmvvm.app.coinList.presentation

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testingmvvm.app.ViewState
import com.example.testingmvvm.domain.model.Coin
import com.example.testingmvvm.domain.useCases.getCoins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase,
):ViewModel(), LifecycleObserver {

    val viewState = MutableLiveData<ViewState<List<Coin>?>>()

    fun fetchCoins() = viewModelScope.launch {

        withContext(Dispatchers.IO){
            getCoinsUseCase.invoke().onEach { result ->
                when(result){

                    is com.example.testingmvvm.common.Resources.Success -> {
                        viewState.value = ViewState.success(result.data)
                    }
                    is com.example.testingmvvm.common.Resources.Error -> {
                        viewState.value = ViewState.error(result.message)
                    }
                    is com.example.testingmvvm.common.Resources.Loading -> {
                        viewState.value = ViewState.loading()
                    }
                }

            }.launchIn(viewModelScope)
        }
    }
}