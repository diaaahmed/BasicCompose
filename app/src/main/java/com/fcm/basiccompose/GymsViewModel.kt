package com.fcm.basiccompose

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "GymsViewModel"

class GymsViewModel(private val stateHandle: SavedStateHandle)
    :ViewModel()
{
    var apiService:ApiService
    //lateinit var apiCall:Call<List<Gym>>

    var state by mutableStateOf(emptyList<Gym>())
    //val job = Job()
   // val scope = CoroutineScope(context = job + Dispatchers.IO)

    private val errorHandler = CoroutineExceptionHandler{_,throwable->
        throwable.printStackTrace()
    }
     fun getGyms(){

         viewModelScope.launch(Dispatchers.IO + errorHandler) {
             val gyms = apiService.getGyms()
             withContext(Dispatchers.Main)
             {
                 state = gyms.restoreSelectedGyms()
             }
         }
//         scope.launch {
//             val gyms = apiService.getGyms()
//             withContext(Dispatchers.Main)
//             {
//                 state = gyms.restoreSelectedGyms()
//             }
//         }
//        apiCall = apiService.getGyms()
//
//        apiCall.enqueue(object : Callback<List<Gym>> {
//            override fun onResponse(call: Call<List<Gym>>, response: Response<List<Gym>>)
//            {
//                response.body()?.let {
//                    state = it.restoreSelectedGyms()
//                }
//            }
//
//            override fun onFailure(call: Call<List<Gym>>, t: Throwable)
//            {
//                t.printStackTrace()
//            }
//
//        })

    }
    init{
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://cairo-gyms-55e15-default-rtdb.firebaseio.com/")
            .build()

        apiService = retrofit.create(ApiService::class.java)

        getGyms()
    }

    fun toggleFavouriteState(gymId:Int)
    {
        val gyms = state.toMutableList()
        val itemIndex = gyms.indexOfFirst { it.id == gymId }
        gyms[itemIndex] = gyms[itemIndex].copy(isFavourite = !gyms[itemIndex].isFavourite)
        storeSelectedGym(gyms[itemIndex])
        state = gyms
    }

    private fun storeSelectedGym(gym: Gym) {
        val savedHandleList = stateHandle.get<List<Int>>(FAV_IDS).orEmpty().toMutableList()
        if (gym.isFavourite){
            savedHandleList.add(gym.id)
        }
        else
        {
            savedHandleList.remove(gym.id)
        }
        stateHandle[FAV_IDS] = savedHandleList
    }

    private fun List<Gym>.restoreSelectedGyms():List<Gym>
    {
        stateHandle.get<List<Int>>(FAV_IDS).let {savedIds->
            savedIds?.forEach { gymId->
                this.find { it.id == gymId}?.isFavourite = true
            }
        }

        return this
    }

    companion object {
        const val FAV_IDS = "favouriteGymsIds"
    }

//    override fun onCleared() {
//        super.onCleared()
//        //apiCall.cancel()
//        job.cancel()
//    }
}