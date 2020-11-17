package com.dev.drinks.app.presentation.filter

import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dev.drinks.app.adapter.FilterListAdapter
import com.dev.drinks.data.api.DrinksApi
import com.dev.drinks.domain.entity.Category
import com.dev.drinks.domain.entity.CategoryList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FilterViewModel : ViewModel(), FilterListAdapter.Listener {

    val categories = MutableLiveData<CategoryList>()
    var disabled = CategoryList.empty()

    init { loadCategories() }

    fun getDisabledFromExtra(intent: Intent) {
        val property = FilterActivity.Extra.DISABLED
        val got = intent.extras?.getParcelable(property) as? CategoryList
        got?.let { disabled = it }
    }

    private fun loadCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val toLoad = DrinksApi.getCategories()
            categories.postValue(toLoad)
        }
    }

    // FilterListAdapter.Listener Impl
    override fun onCheckItem(category: Category) {
        disabled.remove(category)
    }

    override fun onUncheckItem(category: Category) {
        disabled.add(category)
    }

}