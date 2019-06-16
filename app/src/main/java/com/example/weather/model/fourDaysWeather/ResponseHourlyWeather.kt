package com.example.weather.model.fourDaysWeather
import com.google.gson.annotations.SerializedName
data class ResponseHourlyWeather(

	@field:SerializedName("city")
	val city: City? = null,

	@field:SerializedName("cnt")
	val cnt: Int? = null,

	@field:SerializedName("cod")
	val cod: String? = null,

	@field:SerializedName("message")
	val message: Double? = null,

	@field:SerializedName("listHourly")
	val listHourly: List<ListItemHourly>? = null
)