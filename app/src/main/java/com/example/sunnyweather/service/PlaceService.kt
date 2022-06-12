package com.example.sunnyweather.service

import com.example.sunnyweather.response.PlaceResponse
import com.example.sunnyweather.activity.SunnyWeatherApplication
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {

    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query")query: String): Call<PlaceResponse>

}