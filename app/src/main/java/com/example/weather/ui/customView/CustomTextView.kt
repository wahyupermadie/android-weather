package com.example.weather.ui.customView

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.core.view.LayoutInflaterCompat
import com.example.weather.R

class CustomTextView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : TextView(context, attrs, defStyleAttr){
    init {

        LayoutInflater.from(context).inflate(R.layout.custom_view_layout, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it,
                R.styleable.custom_component_attributes, 0, 0)
            val dayWeather = resources.getText(typedArray
                .getResourceId(R.styleable
                    .custom_component_attributes_dateWeather,R.string.app_name))

            typedArray.recycle()
        }
    }
}

