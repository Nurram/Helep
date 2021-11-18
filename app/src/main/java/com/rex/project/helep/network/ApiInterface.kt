package com.rex.project.helep.network

import com.rex.project.helep.model.RouteModels
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface ApiInterface {

    @GET("v2/directions/driving-car")
    fun findUsers(
        @Query("api_key") apiKey: String,
        @QueryMap start: Map<String, String>,
        @QueryMap end: Map<String, String>
    ): Call<RouteModels>
}