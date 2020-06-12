package com.roopattnaik.loginNav.network

import com.roopattnaik.loginNav.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/*
Interface for REST API to be used in Retrofit Implementation
 */

interface WeatherAPI {
    @GET("data/2.5/weather?")
    fun getCurrentWeatherData(@Query("lat") lat: String, @Query("lon") lon: String, @Query("APPID") app_id: String): Call<WeatherResponse>
}