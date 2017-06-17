package com.efemoney.forecast.util

import android.accounts.AccountManager
import android.annotation.SuppressLint
import android.app.Activity
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.support.annotation.ColorRes
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.view.View
import java.lang.RuntimeException

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun Context.color(@ColorRes id: Int)
        = ContextCompat.getColor(this, id)

fun Context.checkPermissionFor(permission: String)
        = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

fun Activity.requestThesePermissions(requestCode: Int, vararg permissions: String)
        = ActivityCompat.requestPermissions(this, permissions, requestCode)

fun Activity.shouldShowRationaleFor(permission: String)
        = ActivityCompat.shouldShowRequestPermissionRationale(this, permission)

fun Activity.show(vararg views: View) = views.forEach { it.visible() }

fun Activity.hide(vararg views: View) = views.forEach { it.gone() }

@SuppressLint("ServiceCast")
inline fun <reified T> Context.service(): T {

    return when (T::class) {

        AccountManager::class -> getSystemService(Context.ACCOUNT_SERVICE) as T

        ConnectivityManager::class -> getSystemService(Context.CONNECTIVITY_SERVICE) as T

        NotificationManager::class -> getSystemService(Context.NOTIFICATION_SERVICE) as T

        else -> throw RuntimeException("System service ${T::class.java.simpleName} does not exist or is not defined. Please use context.getSystemService(\"serviceName\" instead)")
    }
}