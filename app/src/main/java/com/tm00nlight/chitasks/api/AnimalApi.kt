package com.tm00nlight.chitasks.api

import com.tm00nlight.chitasks.Animal
import retrofit2.Call
import retrofit2.http.GET

interface AnimalApi {
    @GET("animals/rand/10")
    fun fetchContents() : Call<List<Animal>>
}