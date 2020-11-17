package com.dev.drinks.domain.entity

import com.google.gson.annotations.SerializedName

data class DrinkList(
    @SerializedName("drinks")
    private val list: List<Drink>
) {


    companion object {
        fun empty() : DrinkList {
            return DrinkList(mutableListOf())
        }
    }


    var category: Category? = null

    fun asList() : List<Drink> {
        return list
    }

    fun asMutableList() : MutableList<Drink> {
        return list.toMutableList()
    }

    fun get(index: Int) : Drink {
        return list[index]
    }

    fun count() : Int {
        return list.count()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DrinkList

        if (list != other.list) return false

        return true
    }

    override fun hashCode(): Int {
        return list.hashCode()
    }


}