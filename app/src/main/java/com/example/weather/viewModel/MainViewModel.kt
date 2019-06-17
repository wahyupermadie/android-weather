package com.example.weather.viewModel

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.model.cuurentWeather.ResponseCurrent
import com.example.weather.model.daysWeather.ResponseWeather
import com.example.weather.model.fourDaysWeather.ResponseFiveDays
import com.example.weather.network.ApiService
import com.example.weather.utils.AppSchedulerProvider
import com.example.weather.utils.Constant
import com.example.weather.utils.SchedulerProvider
import com.example.weather.utils.ViewExtension
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class MainViewModel(private val service : ApiService?) : ViewModel(){
    private lateinit var viewExtension : ViewExtension
    private var currentWeatherItem = MutableLiveData<ResponseCurrent>()
    private var fiveDaysWeatherList = MutableLiveData<ResponseFiveDays>()
    private var daysWeatherList = MutableLiveData<ResponseWeather>()
    val _currentWeatherItem: LiveData<ResponseCurrent> = currentWeatherItem
    val _fiveDaysWeatherList: LiveData<ResponseFiveDays> = fiveDaysWeatherList
    val _daysWeatherList: LiveData<ResponseWeather> = daysWeatherList

    private lateinit var scheduler : SchedulerProvider
    private lateinit var  context: Context
    private var type : Boolean = false
    fun setListener(viewCallback: ViewExtension, context: Context, scheduler : SchedulerProvider, type : Boolean){
        this.viewExtension = viewCallback
        this.context = context
        this.scheduler = scheduler
        this.type = type
    }

    @SuppressLint("CheckResult")
    fun getCurrentWeather() : LiveData<ResponseCurrent>{
        if (!isConnected(ctx = context, type = type)){
            currentWeatherItem.value = null
            viewExtension.showError("No Connection")
        }else{
            service?.getCurrentWeather(Constant.CITY_NAME,Constant.API_KEY)
                ?.observeOn(scheduler.ui())
                ?.subscribeOn(scheduler.io())
                ?.subscribe({
                    currentWeatherItem.value = it
                },{
                    currentWeatherItem.value = null
                    viewExtension.showError(it.localizedMessage)
                })
        }
        return currentWeatherItem
    }

    @SuppressLint("CheckResult")
    fun getFourDaysWeather() : LiveData<ResponseFiveDays>{
        if (!isConnected(ctx = context, type = type)){
            fiveDaysWeatherList.value = null
            viewExtension.showError("No Connection")
        }else{
            service?.getFiveDaysWeather(Constant.CITY_NAME, Constant.API_KEY)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    fiveDaysWeatherList.value = it
                },{
                    viewExtension.showError(it.localizedMessage)
                    fiveDaysWeatherList.value = null
                })
        }
        return fiveDaysWeatherList
    }

    @SuppressLint("CheckResult")
    fun getDaysWeather() : LiveData<ResponseWeather>{
        if (!isConnected(ctx = context, type = type)){
            daysWeatherList.value = null
            viewExtension.showError("No Connection")
        }else{
            service?.getDaysWeather(Constant.CITY_NAME, Constant.API_KEY)
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribeOn(Schedulers.io())
                ?.subscribe({
                    daysWeatherList.value = it
                },{
                    viewExtension.showError(it.localizedMessage)
                    daysWeatherList.value = null
                })
        }
        return daysWeatherList
    }

    fun isConnected(ctx: Context?, type : Boolean) : Boolean {
        if (!type){
            return true
        }
        val connectivityManager = ctx?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.activeNetworkInfo != null
    }
}