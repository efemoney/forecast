package com.efemoney.forecast.ui.base

import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.efemoney.forecast.Application

open class BaseActivity : AppCompatActivity() {

    val application
        get() = getApplication() as Application

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        if (item?.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}