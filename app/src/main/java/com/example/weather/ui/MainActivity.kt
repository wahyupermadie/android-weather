package com.example.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.R
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.utils.ViewExtension
import com.example.weather.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), ViewExtension{
    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showError(s: String) {
        toast(s)
    }

    val viewModel : MainViewModel by viewModel()
    private var binding: ActivityMainBinding? = null
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

        viewModel.getCurrentWeather().observe(this, Observer {
            if(it != null){
                binding?.current = it
                binding?.weather = it.weather?.get(0)
                binding?.isLoadingCurrent = false
            }
        })

        viewModel.getDaysWeather().observe(this, Observer {
            it.list?.let { it1 -> adapter.addWeather(it1) }
            binding?.isLoading4Days = false
            adapter.notifyDataSetChanged()
            rv_five_weather.adapter = adapter
            rv_five_weather.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        })
    }

    private fun init() {
        adapter = MainAdapter()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.setListener(this)
        binding?.isLoadingCurrent = true
        binding?.isLoading4Days = true
    }
}
