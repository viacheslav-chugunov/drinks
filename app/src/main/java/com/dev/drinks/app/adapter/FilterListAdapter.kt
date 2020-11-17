package com.dev.drinks.app.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.dev.drinks.R
import com.dev.drinks.domain.entity.Category
import com.dev.drinks.domain.entity.CategoryList

class FilterListAdapter(
    private val filters: CategoryList,
    private val listener: Listener,
    private val disabled: CategoryList
) : RecyclerView.Adapter<FilterListAdapter.ViewHolder>() {


    interface Listener {
        fun onCheckItem(category: Category)
        fun onUncheckItem(category: Category)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_filter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = filters.get(position)
        val isChecked = disabled.asList().map { it.toString() }.contains(item.toString())
        holder.setItem(item, isChecked)
        holder.setListener(item)
    }

    override fun getItemCount(): Int = filters.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val filter: TextView = view.findViewById(R.id.filter)
        private val checkBox: CheckBox = view.findViewById(R.id.checkbox)

        fun setItem(item: Category, isDisabled: Boolean) {
            filter.text = item.toString()
            checkBox.isChecked = !isDisabled
            if (isDisabled) {
                checkBox.setButtonDrawable(R.drawable.unordered_icon)
            } else {
                checkBox.setButtonDrawable(R.drawable.ordered_icon)
            }
        }

        fun setListener(item: Category) {
            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
                when {
                    isChecked -> {
                        buttonView.setButtonDrawable(R.drawable.ordered_icon)
                        listener.onCheckItem(item)
                    }
                    else -> {
                        buttonView.setButtonDrawable(R.drawable.unordered_icon)
                        listener.onUncheckItem(item)
                    }
                }
            }
        }

    }
}