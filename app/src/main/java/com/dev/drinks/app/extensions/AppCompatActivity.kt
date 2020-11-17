package com.dev.drinks.app.extensions

import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.dev.drinks.R

fun AppCompatActivity.bindToolbar(title: String = getString(R.string.app_name), enableUpButton: Boolean = false) {
    val toolbar: Toolbar = findViewById(R.id.toolbar)
    val toolbarTitle: TextView = findViewById(R.id.toolbar_title)
    setSupportActionBar(toolbar)
    toolbarTitle.text = title
    supportActionBar?.setDisplayShowTitleEnabled(false)
    supportActionBar?.setDisplayHomeAsUpEnabled(enableUpButton)
}

fun AppCompatActivity.bindToolbar(title: Int, enableUpButton: Boolean = false) {
    bindToolbar(getString(title), enableUpButton)
}