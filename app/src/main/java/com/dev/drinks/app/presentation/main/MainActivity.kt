package com.dev.drinks.app.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import com.dev.drinks.R
import com.dev.drinks.app.extensions.bindToolbar
import com.dev.drinks.app.presentation.filter.FilterActivity

class MainActivity : AppCompatActivity() {


    object Extra { const val DISABLED = "disabled" }


    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bindToolbar()
        setupViewModel()
        observeData()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getDisabledFromExtra(intent)
        viewModel.loadCategories()
    }

    private fun observeData() {
        viewModel.categories.observe(this) {
            viewModel.loadDrinks()
        }

        val list = supportFragmentManager.findFragmentById(R.id.list) as MainListFragment
        viewModel.drinks.observe(this) {
            val drinks = viewModel.drinks.value ?: emptyList()
            list.updateAdapter(drinks)
        }
    }

    // Menu Impl
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.filter_item -> { startFilterActivity(); true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun startFilterActivity() {
        val intent = Intent(this, FilterActivity::class.java)
        intent.putExtra(FilterActivity.Extra.DISABLED, viewModel.disabled)
        startActivity(intent)
    }

}