package com.example.testingmvvm.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testingmvvm.R
import com.example.testingmvvm.app.coinList.CoinListFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.mainFrameLayout, CoinListFragment())
            commit()
        }
    }
}


