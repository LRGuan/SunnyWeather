package com.example.sunnyweather

import android.content.Context
import androidx.core.content.edit
import com.example.sunnyweather.activity.SunnyWeatherApplication
import com.example.sunnyweather.response.Place
import com.google.gson.Gson

object PlaceDao {

    private fun sharePreferences() = SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

    fun savePlace(place: Place){
        sharePreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace():Place{
        val placeJson = sharePreferences().getString("place","")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    fun isPlaceSaved() = sharePreferences().contains("place")

}