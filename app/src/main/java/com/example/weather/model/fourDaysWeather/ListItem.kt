package com.example.weather.model.fourDaysWeather
import com.example.weather.model.Clouds
import com.example.weather.model.WeatherItem
import com.example.weather.model.Wind
import com.google.gson.annotations.SerializedName

data class ListItem(

	@field:SerializedName("dt")
	val dt: Long? = null,

	@field:SerializedName("dt_txt")
	val dtTxt: String? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem>? = null,

	@field:SerializedName("main")
	val main: Main? = null,

	@field:SerializedName("clouds")
	val clouds: Clouds? = null,

	@field:SerializedName("sys")
	val sys: Sys? = null,

	@field:SerializedName("wind")
	val wind: Wind? = null,

	@field:SerializedName("rain")
	val rain: Rain? = null
)