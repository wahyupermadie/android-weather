package com.example.weather.model.daysWeather
import com.example.weather.model.WeatherItem
import com.google.gson.annotations.SerializedName
data class ListItem(

	@field:SerializedName("dt")
	val dt: Int? = null,

	@field:SerializedName("temp")
	val temp: Temp? = null,

	@field:SerializedName("deg")
	val deg: Int? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem?>? = null,

	@field:SerializedName("humidity")
	val humidity: Int? = null,

	@field:SerializedName("pressure")
	val pressure: Double? = null,

	@field:SerializedName("clouds")
	val clouds: Int? = null,

	@field:SerializedName("speed")
	val speed: Double? = null,

	@field:SerializedName("rain")
	val rain: Double? = null
)