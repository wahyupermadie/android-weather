package com.example.weather.ui
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.model.daysWeather.ListItem

class MainAdapter() : RecyclerView.Adapter<MainAdapter.ViewHolder>() {
    private var list : List<ListItem> = listOf()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context)
            .inflate(R.layout.weather_four_items, parent, false))
    }

    fun addWeather (list : List<ListItem>){
        Log.d("DATA_DAYS","DATA "+list)
        this.list = list
    }
    override fun getItemCount(): Int {
        Log.d("DATA_DAYS","DATA "+list.size)
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        list.get(position).let { holder.bindItem(it) }
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view){
        fun bindItem(listItem: ListItem) {

        }
    }
}