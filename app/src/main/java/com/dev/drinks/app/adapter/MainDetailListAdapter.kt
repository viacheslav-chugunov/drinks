package com.dev.drinks.app.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.dev.drinks.R

import com.dev.drinks.domain.entity.DrinkList
import com.squareup.picasso.Picasso


class MainDetailListAdapter(
    private val context: Context,
    private val drinks: DrinkList
) : RecyclerView.Adapter<MainDetailListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_main_detail, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = drinks.get(position)
        item.imageUrl?.let {
            Picasso.with(context)
                .load(it.trim())
                .into(holder.image)
        }
        holder.name.text = item.name
    }

    override fun getItemCount(): Int = drinks.count()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.image)
        val name: TextView = view.findViewById(R.id.name)
    }
}