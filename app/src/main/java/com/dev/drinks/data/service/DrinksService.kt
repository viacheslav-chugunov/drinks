package com.dev.drinks.data.service

import com.dev.drinks.domain.entity.CategoryList
import com.dev.drinks.domain.entity.DrinkList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface DrinksService {

    @GET("filter.php")
    fun getDrinkByCategory(@Query("c") category: String): Call<DrinkList>

    @GET("list.php?c=list")
    fun getDrinkCategories(): Call<CategoryList>

}