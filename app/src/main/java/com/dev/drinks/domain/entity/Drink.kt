package com.dev.drinks.domain.entity

import com.google.gson.annotations.SerializedName

data class Drink(
    @SerializedName("idDrink")
    val id: String? = null,
    @SerializedName("strDrink")
    val name: String? = null,
    @SerializedName("strCategory")
    var category: String? = null,
    @SerializedName("strDrinkThumb")
    val imageUrl: String? = null
)