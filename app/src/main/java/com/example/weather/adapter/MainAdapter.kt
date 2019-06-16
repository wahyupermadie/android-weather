package com.example.weather.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.WeatherFourItemsBinding
import com.example.weather.model.fourDaysWeather.ListItem
import retrofit2.adapter.rxjava2.Result.response
import android.R.string



class MainAdapter(private var fourDaysList : List<ListItem>?) : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<WeatherFourItemsBinding>(
            LayoutInflater.from(parent.context),
            R.layout.weather_four_items, parent, false
        )

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (fourDaysList?.size == null) 0 else fourDaysList!!.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.view.fourDaysList = fourDaysList?.get(position)
        holder.view.weather = fourDaysList?.get(position)?.weather?.get(0)
    }

    class ViewHolder(val view : WeatherFourItemsBinding) : RecyclerView.ViewHolder(view.root)
}