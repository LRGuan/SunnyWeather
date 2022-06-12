package com.example.sunnyweather.model

import com.example.sunnyweather.model.DailyResponse
import com.example.sunnyweather.model.RealtimeResponse

data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)