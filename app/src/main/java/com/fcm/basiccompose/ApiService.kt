package com.fcm.basiccompose

import retrofit2.http.GET

interface ApiService
{
    @GET("gyms.json")
    suspend fun getGyms():List<Gym>
}