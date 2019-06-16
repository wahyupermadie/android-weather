package com.example.weather.utils

import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.TextViewCompat
import androidx.databinding.BindingAdapter
import androidx.databinding.adapters.TextViewBindingAdapter
import com.bumptech.glide.Glide

class BindingExtension{
    companion object{

        @JvmStatic
        @BindingAdapter("imgSrc")
        fun setImageUrl(view: AppCompatImageView, url : String){
            Glide.with(view.context)
                .asBitmap()
                .load(String.format("%s%s%s",Constant.ICON_URL,url,Constant.ICON_EXTENTION))
                .into(view)
        }

        @JvmStatic
        @BindingAdapter("textTemperature")
        fun convertToCelcius(view : TextView, value : String?){
            if (!value.equals("null")) {
                val temp = value?.toDouble()
                val newTemp = temp?.minus(273.15)
                view.text = String.format("%.1f%s", newTemp, 0x00B0.toChar())
            }
        }

        @JvmStatic
        @BindingAdapter("isVisibilityCurrent")
        fun setVisibilityCurrent(view:View, show:Boolean){
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("isVisibility4Days")
        fun setVisibility4Days(view:View, show:Boolean){
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("isVisibilityDays")
        fun setVisibilityDays(view:View, show:Boolean){
            view.visibility = if (show) View.VISIBLE else View.GONE
        }

        @JvmStatic
        @BindingAdapter("dayWeatherName")
        fun setDayName(view: AppCompatTextView, text : String){
            view.text = Extension.convertToDay(text)
        }

        @JvmStatic
        @BindingAdapter("dateWeather")
        fun setDateWeather(view: AppCompatTextView, text : String){
            view.text = Extension.convertDate(text)
        }
    }
}