package com.example.easyfoodcookapp.viewModel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.easyfoodcookapp.pojo.Meal
import com.example.easyfoodcookapp.pojo.MealsList
import com.example.easyfoodcookapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel: ViewModel() {

    private var randomMealLiveData= MutableLiveData<Meal>()

    fun getRandomMeal(){

        RetrofitInstance.api.randomMeal().enqueue(object : Callback<MealsList> {

            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {


                if(response.body()!= null){
                    val randomMeals: Meal = response.body()!!.meals[0]
                    randomMealLiveData.value= randomMeals
                   /* Glide.with(this@HomeFragment)
                        .load(randomMeals.strMealThumb)
                        .into(binding.randomMeal)*/
                }else{
                    return
                }

            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.d("Home Fragment Error",t.message.toString())
            }
        })
    }
    fun observeRandomMealLiveData():LiveData<Meal>{
        return randomMealLiveData
    }
}