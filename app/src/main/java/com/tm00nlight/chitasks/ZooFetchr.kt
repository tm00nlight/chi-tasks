package com.tm00nlight.chitasks

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tm00nlight.chitasks.api.AnimalApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val TAG = "ZooFetchr"

class ZooFetchr {
    private val animalApi: AnimalApi

    init {
        val retrofit : Retrofit = Retrofit.Builder()
            .baseUrl("https://zoo-animal-api.herokuapp.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        animalApi = retrofit.create(AnimalApi::class.java)
    }

    fun fetchContents() : LiveData<List<Animal>> {
        val responseLiveData : MutableLiveData<List<Animal>> = MutableLiveData()

        val zooRequest : Call<List<Animal>> = animalApi.fetchContents()
        zooRequest.enqueue(object : Callback<List<Animal>> {
            override fun onFailure(call: Call<List<Animal>>, t: Throwable) {
                Log.e(TAG, "Failed to fetch data", t)
            }

            override fun onResponse(call: Call<List<Animal>>, response: Response<List<Animal>>) {
                Log.d(TAG, "Response received")
                //val animalResponse : List<Animal>? = response.body()
                val animals: List<Animal> = response.body()?: mutableListOf()
                responseLiveData.value = animals
                Log.d(TAG, "Response consists of ${animals.size} animals")
            }
        })

        return responseLiveData
    }
}