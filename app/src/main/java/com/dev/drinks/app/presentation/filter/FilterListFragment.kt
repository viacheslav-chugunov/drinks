package com.dev.drinks.app.presentation.filter

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dev.drinks.R
import com.dev.drinks.app.adapter.FilterListAdapter
import com.dev.drinks.domain.entity.CategoryList

class FilterListFragment : Fragment() {

    private lateinit var recycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        recycler = inflater.inflate(R.layout.fragment_filter_list, container, false) as RecyclerView
        recycler.layoutManager = LinearLayoutManager(context)
        return recycler
    }

    fun updateAdapter(filters: CategoryList,
                      listener: FilterListAdapter.Listener,
                      disabled: CategoryList) {
        recycler.adapter = FilterListAdapter(filters, listener,disabled)
    }

}