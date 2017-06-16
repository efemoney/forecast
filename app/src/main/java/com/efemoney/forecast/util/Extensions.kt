package com.efemoney.forecast.util

import android.content.Context
import android.os.Build
import android.support.annotation.ColorRes
import android.support.v4.content.ContextCompat
import android.view.View

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Context.color(@ColorRes id: Int) = ContextCompat.getColor(this, id)

private fun sdkVersionGreater(code: Int) = Build.VERSION.SDK_INT >= code
