package com.example.easyfoodcookapp.retrofit

import com.example.easyfoodcookapp.pojo.MealsList
import retrofit2.Call
import retrofit2.http.GET

interface MealsApi {

    @GET("random.php")
    fun randomMeal(): Call<MealsList>
}