package com.example.weather.utils

import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

class Extension{
    companion object{

        fun convertToDay(s : String) : String{

            val sdf = SimpleDateFormat("yyyy-mm-dd hh:mm:ss", Locale.US)
            val newDate = sdf.parse(s)
            val localeIndonesia = Locale("id", "ID")
            return SimpleDateFormat("EEEE hh:mm a", localeIndonesia).format(newDate)
        }

        fun convertDate(s : String) : String{
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = s.toLong()

            val localeIndonesia = Locale("id", "ID")
            return SimpleDateFormat("EEEE hh:mm a", localeIndonesia).format(calendar.time)
        }
    }
}