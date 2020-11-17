package com.dev.drinks.data.api

import com.dev.drinks.domain.entity.Category
import com.dev.drinks.domain.entity.CategoryList
import com.dev.drinks.domain.entity.DrinkList
import com.dev.drinks.data.service.DrinksService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DrinksApi {

    private const val BASE_URL = "https://www.thecocktaildb.com/api/json/v1/"
    private const val API_KEY = "1"

    private val retrofit: Retrofit by lazy {
        synchronized(this) {
            Retrofit.Builder()
                .baseUrl("$BASE_URL$API_KEY/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }

    private val service: DrinksService by lazy {
        retrofit.create(DrinksService::class.java)
    }

    fun getDrinksByCategory(category: Category): DrinkList =
        service.getDrinkByCategory(category.toString()).execute().body() ?: DrinkList.empty()

    fun getCategories() : CategoryList =
        service.getDrinkCategories().execute().body() ?: CategoryList.empty()

    fun getCategories(disabled: CategoryList) : CategoryList =
        getCategories().apply { erase(disabled) }


}