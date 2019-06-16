package com.example.weather.network

import com.example.weather.model.cuurentWeather.ResponseCurrent
import com.example.weather.model.daysWeather.ResponseWeather
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService{

    @GET("weather")
    fun getCurrentWeather(@Query("q") city:String,
                          @Query("appid") appId:String) : Observable<ResponseCurrent>

    @GET("forecast/daily")
    fun getDaysWeather(@Query("q") city:String,
                       @Query("appid") appId:String) : Observable<ResponseWeather>
}