package com.example.sunnyweather

import androidx.lifecycle.liveData
import com.example.sunnyweather.model.Weather
import com.example.sunnyweather.network.SunnyWeatherNetwork
import com.example.sunnyweather.response.Place
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.Exception
import java.lang.RuntimeException

object Repository {

    fun searchPlaces(query:String) = liveData(Dispatchers.IO) {
        val result = try{
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok"){
                val places = placeResponse.places
                Result.success(places)
            }else{
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        }catch (e:Exception){
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }

    fun refreshWeather(lng:String, lat:String) = liveData(Dispatchers.IO) {
            val result = try{
                coroutineScope {
                    val deferredRealtime = async {
                        SunnyWeatherNetwork.getRealtimeWeather(lng, lat)
                    }
                    val deferredDaily = async {
                        SunnyWeatherNetwork.getDailyWeather(lng ,lat)
                    }
                    val realtimeResponse = deferredRealtime.await()
                    val dailyResponse = deferredDaily.await()
                    if (realtimeResponse.status == "ok" && dailyResponse.status == "ok"){
                        val weather = Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                        Result.success(weather)
                    }else{
                        Result.failure(RuntimeException("realtime status:${realtimeResponse.status},daily status:${dailyResponse.status}"))
                    }
                }
            }catch (e:Exception){
                Result.failure<Weather>(e)
            }
        emit(result)
    }

    fun savePlace(place:Place) = PlaceDao.savePlace(place)

    fun getPlace() = PlaceDao.getSavedPlace()

    fun isPlaceSaved() = PlaceDao.isPlaceSaved()

}