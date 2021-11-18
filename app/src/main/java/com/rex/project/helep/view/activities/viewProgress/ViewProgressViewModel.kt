package com.rex.project.helep.view.activities.viewProgress

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import com.rex.project.helep.MainRepository
import com.rex.project.helep.model.RouteModels
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewProgressViewModel(
    private val mainRepository: MainRepository
): ViewModel() {
    private val loading = MutableLiveData(true)
    private val routes = MutableLiveData<List<LatLng>>(null)
    private val error = MutableLiveData<String>()

    fun getTaskById(id: Long) = mainRepository.getTaskById(id)

    fun updateTaskStatus(id: Long, winnerId: Long, status: String) = viewModelScope.launch {
        mainRepository.updateTaskStatus(id, winnerId, status)
    }

    fun getRoute(origin: Map<String, String>, destination: Map<String, String>) {
        mainRepository.getRoute(origin, destination).enqueue(object : Callback<RouteModels> {
            override fun onResponse(call: Call<RouteModels>, response: Response<RouteModels>) {
                routes.postValue(parseResponse(response.body()))
                loading.postValue(false)
            }

            override fun onFailure(call: Call<RouteModels>, t: Throwable) {
                error.postValue(t.message)
            }
        })
    }

    private fun parseResponse(routeModels: RouteModels?): List<LatLng> {
        val features = routeModels?.features?.get(0)
        val geometry = features?.geometry
        val coordinates = geometry?.coordinates
        val parsedCoordinates = arrayListOf<LatLng>()

        if (coordinates != null) {
            for (item: List<Double> in coordinates) {
                parsedCoordinates.add(LatLng(item[1], item[0]))
            }
        }

        return parsedCoordinates
    }

    fun getRoutes(): LiveData<List<LatLng>> = routes
    fun getLoading(): LiveData<Boolean> = loading
    fun getError(): LiveData<String> = error
}