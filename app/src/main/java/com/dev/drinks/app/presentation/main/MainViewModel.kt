package com.dev.drinks.app.presentation.main

import android.content.Intent
import android.os.Handler
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.drinks.data.api.DrinksApi
import com.dev.drinks.domain.entity.CategoryList
import com.dev.drinks.domain.entity.DrinkList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    val drinks = MutableLiveData<List<DrinkList>>()
    val categories = MutableLiveData<CategoryList>()
    var disabled = CategoryList.empty()
    var isListLoaded = MutableLiveData(false)

    fun getDisabledFromExtra(intent: Intent) {
        val property = MainActivity.Extra.DISABLED
        val got = intent.extras?.getParcelable(property) as? CategoryList
        got?.let { disabled = it }
    }

    fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val toLoad = DrinksApi.getCategories(disabled)
            categories.postValue(toLoad)
        }
    }

    fun loadDrinks() {
        viewModelScope.launch(Dispatchers.IO) {
            val toLoad = mutableListOf<DrinkList>()
            val categories = categories.value?.asList() ?: emptyList()
            categories.forEach { category ->
                toLoad += DrinksApi.getDrinksByCategory(category)
                    .also { it.category = category }
            }
            drinks.postValue(toLoad)
            isListLoaded.postValue(true)
        }
    }

}