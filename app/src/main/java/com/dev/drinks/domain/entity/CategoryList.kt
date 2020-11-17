package com.dev.drinks.domain.entity

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

data class CategoryList(
    @SerializedName("drinks")
    private val list: MutableList<Category>
) : Parcelable {

    fun asList() : List<Category> {
        return list
    }

    fun asMutableList() : MutableList<Category> {
        return list
    }

    fun get(index: Int): Category {
        return list[index]
    }

    fun count() : Int {
        return list.count()
    }

    fun erase(categories: CategoryList) {
        list -= categories.asList()
    }

    fun add(category: Category) {
        list.add(category)
    }

    fun remove(category: Category) {
        list.remove(category)
    }

    // Parcelable Impl
    constructor(parcel: Parcel) : this(parcel.createTypedArrayList(Category)!!)

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeTypedList(list)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<CategoryList> {

        fun empty() : CategoryList {
            return CategoryList(mutableListOf())
        }

        override fun createFromParcel(parcel: Parcel): CategoryList {
            return CategoryList(parcel)
        }

        override fun newArray(size: Int): Array<CategoryList?> {
            return arrayOfNulls(size)
        }

    }

}