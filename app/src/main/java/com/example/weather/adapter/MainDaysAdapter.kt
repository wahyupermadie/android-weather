package com.example.weather.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.WeatherListBinding
import com.example.weather.model.daysWeather.ListItem

class MainDaysAdapter(private var daysList : List<ListItem>) : RecyclerView.Adapter<MainDaysAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<WeatherListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_list, parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return daysList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.list = daysList.get(position)
        holder.view.weather = daysList.get(position).weather?.get(0)
    }

    class ViewHolder(val view : WeatherListBinding) : RecyclerView.ViewHolder(view.root)
}