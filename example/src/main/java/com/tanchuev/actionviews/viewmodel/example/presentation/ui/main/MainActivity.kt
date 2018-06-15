package com.tanchuev.actionviews.viewmodel.example.presentation.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.tanchuev.actionviews.viewmodel.example.presentation.R
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Screens
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.activity.BaseActivity

class MainActivity : BaseActivity() {
    override val layout: Int = R.layout.ac_fragment_container
    override val startFragment: String? = Screens.AllGifts

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.actionEmptyResponse -> {}

        }
        return super.onOptionsItemSelected(item)
    }
}