package com.example.mvi_retrofit_learning.api

import com.example.mvi_retrofit_learning.model.Animal
import retrofit2.http.GET

interface AnimalApi {
    @GET("animals.json")
    suspend fun getAnimals(): List<Animal>
}