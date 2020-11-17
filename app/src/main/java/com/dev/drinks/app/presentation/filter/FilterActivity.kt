package com.dev.drinks.app.presentation.filter

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.dev.drinks.R
import com.dev.drinks.app.extensions.bindToolbar
import com.dev.drinks.app.presentation.main.MainActivity

class FilterActivity : AppCompatActivity() {


    object Extra { const val DISABLED = "disabled" }


    private lateinit var viewModel: FilterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)
        bindToolbar(title = R.string.filters, enableUpButton = true)
        viewModel = ViewModelProvider(this).get(FilterViewModel::class.java)
        viewModel.getDisabledFromExtra(intent)
        observeData()
    }

    private fun observeData() {
        viewModel.categories.observe(this) {
            val list = supportFragmentManager.findFragmentById(R.id.filter_list) as FilterListFragment
            list.updateAdapter(it, viewModel, viewModel.disabled)
        }
    }

    fun onClickApplyButton(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra(MainActivity.Extra.DISABLED, viewModel.disabled)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }

}