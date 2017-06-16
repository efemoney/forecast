package com.efemoney.forecast.ui.today

import android.os.Bundle
import com.efemoney.forecast.R
import com.efemoney.forecast.ui.base.BaseActivity
import kotlinx.android.synthetic.main.appbar.*

class TodayActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today)

        setupToolbar()
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
