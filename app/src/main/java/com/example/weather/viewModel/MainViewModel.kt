package com.example.weather.viewModel

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.weather.model.cuurentWeather.ResponseCurrent
import com.example.weather.model.daysWeather.ResponseWeather
import com.example.weather.model.fourDaysWeather.ResponseHourlyWeather
import com.example.weather.network.ApiService
import com.example.weather.utils.Constant
import com.example.weather.utils.ViewExtension
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val service : ApiService, application: Application) : AndroidViewModel(application){
    private lateinit var viewExtension : ViewExtension
    private var currentWeatherItem = MutableLiveData<ResponseCurrent>()
    private var daysWeatherList = MutableLiveData<ResponseWeather>()
    private var hourlyWeatherList = MutableLiveData<ResponseHourlyWeather>()

    fun setListener(viewCallback: ViewExtension){
        this.viewExtension = viewCallback
    }

    @SuppressLint("CheckResult")
    fun getCurrentWeather() : LiveData<ResponseCurrent>{
        service.getCurrentWeather(Constant.CITY_NAME,Constant.API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                currentWeatherItem.value = it
                viewExtension.showError("hahahah")
            },{
                currentWeatherItem.value = null
                viewExtension.showError("hahahah")
            })
        return currentWeatherItem
    }

    @SuppressLint("CheckResult")
    fun getDaysWeather() : LiveData<ResponseWeather>{
        service.getDaysWeather(Constant.CITY_NAME, Constant.API_KEY)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                daysWeatherList.value = it
                Log.d("DATA_DAYS","DATA "+it)
            },{
                daysWeatherList.value = null
                viewExtension.showError("hahahah")
                Log.d("DATA_DAYS","DATA "+it)
            })
        return daysWeatherList
    }
}