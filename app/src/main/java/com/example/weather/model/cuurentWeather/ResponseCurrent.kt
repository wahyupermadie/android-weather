package com.example.weather.model.cuurentWeather
import com.example.weather.model.Clouds
import com.example.weather.model.Coord
import com.example.weather.model.WeatherItem
import com.example.weather.model.Wind
import com.google.gson.annotations.SerializedName
data class ResponseCurrent(

	@field:SerializedName("dt")
	val dt: Int? = null,

	@field:SerializedName("coord")
	val coord: Coord? = null,

	@field:SerializedName("visibility")
	val visibility: Int? = null,

	@field:SerializedName("weather")
	val weather: List<WeatherItem>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("cod")
	val cod: Int? = null,

	@field:SerializedName("main")
	val main: Main? = null,

	@field:SerializedName("clouds")
	val clouds: Clouds? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("sys")
	val sys: Sys? = null,

	@field:SerializedName("base")
	val base: String? = null,

	@field:SerializedName("wind")
	val wind: Wind? = null
)