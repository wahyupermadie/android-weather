package com.example.weather.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weather.databinding.ActivityMainBinding
import com.example.weather.utils.ViewExtension
import com.example.weather.viewModel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast
import org.koin.android.viewmodel.ext.android.viewModel
import android.view.View
import android.view.animation.AnimationUtils.loadAnimation
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.weather.adapter.MainAdapter
import com.example.weather.adapter.MainDaysAdapter
import org.jetbrains.anko.contentView
import org.jetbrains.anko.design.snackbar
import android.graphics.drawable.ClipDrawable.VERTICAL
import com.example.weather.R


class MainActivity : AppCompatActivity(), ViewExtension{
    val viewModel : MainViewModel by viewModel()
    var isUp : Boolean = false
    private var binding: ActivityMainBinding? = null
    private lateinit var adapter: MainAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        getCurrent()
        getFiveDays()
    }

    override fun onResume() {
        super.onResume()
        if (viewModel.isConnected(this)){
            getCurrent()
            getFiveDays()
        }
    }
    private fun init() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel.setListener(this)
        binding?.isLoadingCurrent = true
        binding?.isLoading4Days = true
        binding?.isLoadingDays = true

        my_view.visibility = View.GONE
        next_days_button.setOnClickListener {
            if (isUp){
                slideDown(my_view)
            }else{
                slideUp(my_view)
            }
        }
    }

    fun slideUp(view: View) {
        next_days_button.text = "Back"
        isUp = true
        view.visibility = View.VISIBLE
        val slideUp = loadAnimation(
            applicationContext,
            R.anim.slide_up
        )
        view.startAnimation(slideUp)
        getWeatherDays()
    }

    fun slideDown(view : View){
        next_days_button.text = "Next 15 Days"
        isUp = false
        view.visibility = View.GONE
        val slideUp = loadAnimation(
            applicationContext,
            R.anim.slide_down
        )
        view.startAnimation(slideUp)
    }

    override fun showError(s: String) {
        contentView?.snackbar(s)
    }

    fun getWeatherDays(){
        viewModel.getDaysWeather().observe(this, Observer {
            if (it != null){
                val adapter = it.list?.let { it1 -> MainDaysAdapter(it1) }
                rv_next_days.adapter = adapter
                val decoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
                rv_next_days.addItemDecoration(decoration)
                binding?.isLoadingDays = false
            }
        })
    }

    fun getCurrent(){
        viewModel.getCurrentWeather().observe(this, Observer {
            if(it != null){
                binding?.current = it
                binding?.weather = it.weather?.get(0)
                binding?.isLoadingCurrent = false
            }
        })
    }

    fun getFiveDays(){
        viewModel.getFourDaysWeather().observe(this, Observer {
            if (it != null){
                adapter = it.list?.let { it1 -> MainAdapter(it1) }!!
                binding?.isLoading4Days = false
                adapter.notifyDataSetChanged()
                rv_five_weather.adapter = adapter
                rv_five_weather.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            }
        })
    }
}
