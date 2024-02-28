package com.example.easyfoodcookapp.fragment


import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.bumptech.glide.Glide

import com.example.easyfoodcookapp.databinding.FragmentHomeBinding
import com.example.easyfoodcookapp.pojo.Meal


import com.example.easyfoodcookapp.viewModel.HomeViewModel


class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        homeViewModel= ViewModelProvider(this)[HomeViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.progressBar.visibility= View.VISIBLE

        homeViewModel.getRandomMeal()
        ObserverRandomMeal()

        /*RetrofitInstance.api.randomMeal().enqueue(object : Callback<MealsList> {

            override fun onResponse(call: Call<MealsList>, response: Response<MealsList>) {

                binding.progressBar.visibility= View.GONE
                if(response.body()!= null){
                    val randomMeals: Meal = response.body()!!.meals[0]
                    Glide.with(this@HomeFragment)
                        .load(randomMeals.strMealThumb)
                        .into(binding.randomMeal)
                }else{
                    return
                }

            }

            override fun onFailure(call: Call<MealsList>, t: Throwable) {
                Log.d("Home Fragment Error",t.message.toString())
            }
        })*/
    }

    private fun ObserverRandomMeal() {
        homeViewModel.observeRandomMealLiveData().observe(viewLifecycleOwner,object : Observer<Meal>{
            override fun onChanged(value: Meal) {
                binding.progressBar.visibility=View.GONE
                Glide.with(this@HomeFragment)
                    .load(value.strMealThumb)
                    .into(binding.randomMeal)
            }

        })
    }

}