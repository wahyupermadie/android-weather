package com.example.weather.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.weather.network.ApiService

class MainViewModel(service : ApiService, application: Application) : AndroidViewModel(application){

}