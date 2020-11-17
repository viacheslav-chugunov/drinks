package com.dev.drinks.app.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.drinks.R
import com.dev.drinks.app.adapter.MainCategoryListAdapter
import com.dev.drinks.domain.entity.DrinkList

class MainListFragment : Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        recycler = inflater.inflate(R.layout.fragment_main_list, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(context)
        return recycler
    }

    fun updateAdapter(drinksByCategory: List<DrinkList>) {
        recycler.adapter = MainCategoryListAdapter(drinksByCategory)
    }

}
