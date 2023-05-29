package com.fcm.basiccompose

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GymsDetailsViewModel(private val stateHandle: SavedStateHandle): ViewModel()
{
    var state = mutableStateOf<Gym?>(null)

    var apiService:ApiService

    private val errorHandler = CoroutineExceptionHandler{_,throwable->
        throwable.printStackTrace()
    }

    init{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cairo-gyms-55e15-default-rtdb.firebaseio.com/")
            .build()

        apiService = retrofit.create(ApiService::class.java)

        val gymId = stateHandle.get<Int>("gym_id") ?: 0

        getGym(gymId)
    }

    fun getGym(id:Int)
    {
        viewModelScope.launch(Dispatchers.IO + errorHandler) {
            val gyms = apiService.getGym(id).values.first()
            state.value = gyms
        }
    }

}