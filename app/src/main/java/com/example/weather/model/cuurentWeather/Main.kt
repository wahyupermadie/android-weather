package com.example.weather.model.cuurentWeather

import com.google.gson.annotations.SerializedName
data class Main(

	@field:SerializedName("temp")
	val temp: String? = null,

	@field:SerializedName("temp_min")
	val tempMin: Double? = null,

	@field:SerializedName("humidity")
	val humidity: String? = null,

	@field:SerializedName("pressure")
	val pressure: Int? = null,

	@field:SerializedName("temp_max")
	val tempMax: Double? = null
)