package com.example.weather.viewModel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.weather.model.cuurentWeather.ResponseCurrent
import com.example.weather.model.daysWeather.ResponseWeather
import com.example.weather.model.fourDaysWeather.ResponseFiveDays
import com.example.weather.network.ApiService
import com.example.weather.utils.AppSchedulerProvider
import com.example.weather.utils.SchedulerProvider
import com.example.weather.utils.TestSchedulerProvider
import com.example.weather.utils.ViewExtension
import com.google.common.truth.Truth
import org.junit.Before
import org.junit.Test
import org.junit.Rule
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
class MainViewModelTest {

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var viewExtension: ViewExtension

    @Mock
    private
    lateinit var scheduler: SchedulerProvider

    private  var currentList = mutableListOf<ResponseCurrent>()
    private  var fiveDaysList = mutableListOf<ResponseFiveDays>()
    private  var daysList = mutableListOf<ResponseWeather>()

    @InjectMocks
    private lateinit var viewModel : MainViewModel

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this )
        scheduler = TestSchedulerProvider()
        viewModel.setListener(viewExtension, context, scheduler, false)
    }

    @Test
    fun getCurrentWeather() {
        val currentWeather = viewModel._currentWeatherItem.testObserver()
        viewModel.getCurrentWeather()
        Truth.assert_()
            .that(currentWeather.observedValues)
            .isEqualTo(currentList)

    }

    @Test
    fun getFourDaysWeather() {
        val fiveDays = viewModel._fiveDaysWeatherList.testObserver()
        viewModel.getFourDaysWeather()
        Truth.assert_()
            .that(fiveDays.observedValues)
            .isEqualTo(fiveDaysList)
    }

    @Test
    fun getDaysWeather() {
        val daysWeather = viewModel._daysWeatherList.testObserver()
        viewModel.getDaysWeather()
        Truth.assert_()
            .that(daysWeather.observedValues)
            .isEqualTo(daysList)
    }
}