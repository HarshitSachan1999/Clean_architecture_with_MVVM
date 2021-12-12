package com.example.testingmvvm.app.coinList.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.testingmvvm.R
import com.example.testingmvvm.domain.model.Coin

class CoinsRvAdapter(
    private val context:Context,
    private val coinsList:ArrayList<Coin>
) : RecyclerView.Adapter<CoinsRvAdapter.ViewHolder>() {

    var onItemClick : ((String)->Unit)? = null

    inner class ViewHolder(v:View):RecyclerView.ViewHolder(v){
        val name:TextView = v.findViewById(R.id.coinName)
        val cl:ConstraintLayout = v.findViewById(R.id.coinsCl)

        init {
            cl.setOnClickListener {
                onItemClick?.invoke(it.tag.toString())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.custom_coins_list,
                    parent,
                    false
                )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = "${coinsList[position].name} (${coinsList[position].symbol})"
        holder.cl.tag = coinsList[position].id
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }
}