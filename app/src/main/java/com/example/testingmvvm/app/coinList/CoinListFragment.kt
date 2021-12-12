package com.example.testingmvvm.app.coinList

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testingmvvm.R
import com.example.testingmvvm.app.ViewState
import com.example.testingmvvm.app.coinDetail.CoinDetailsFragment
import com.example.testingmvvm.app.coinList.adapter.CoinsRvAdapter
import com.example.testingmvvm.app.coinList.presentation.CoinListViewModel
import com.example.testingmvvm.domain.model.Coin
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_coin_list.*

@AndroidEntryPoint
class CoinListFragment : Fragment() {

    var coinsList:ArrayList<Coin> = arrayListOf()
    private val viewModel:CoinListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_coin_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        coinsRv.layoutManager = LinearLayoutManager(requireContext())
        val adapter = CoinsRvAdapter(requireContext(),coinsList)
        coinsRv.adapter = adapter

        adapter.onItemClick = {
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.mainFrameLayout, CoinDetailsFragment(it))
                addToBackStack(null)
                commit()
            }
        }

        viewModel.fetchCoins()
        viewModel.viewState.observe(requireActivity(),{
            if(it.status == ViewState.Status.SUCCESS){
                coinsList.addAll(it.data!!)
                adapter.notifyDataSetChanged()
            }
        })
    }


}