package com.fcm.basiccompose

import retrofit2.Call
import retrofit2.http.GET

interface ApiService
{
    @GET("gyms.json")
    fun getGyms():Call<List<Gym>>
}