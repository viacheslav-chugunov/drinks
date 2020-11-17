package com.dev.drinks.app.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.drinks.R
import com.dev.drinks.domain.entity.DrinkList

class MainCategoryListAdapter(
    private val drinksByCategory: List<DrinkList>
) : RecyclerView.Adapter<MainCategoryListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_category, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = drinksByCategory[position]
        holder.category.text = item.category.toString()
        holder.list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = MainDetailListAdapter(context, item)
        }
    }

    override fun getItemCount(): Int = drinksByCategory.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val category: TextView = view.findViewById(R.id.category_name)
        val list: RecyclerView = view.findViewById(R.id.detail_list) as RecyclerView

    }

}