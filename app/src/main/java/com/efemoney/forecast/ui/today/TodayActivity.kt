package com.efemoney.forecast.ui.today

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.design.widget.Snackbar
import com.bumptech.glide.Glide
import com.efemoney.forecast.R
import com.efemoney.forecast.data.model.Location
import com.efemoney.forecast.ui.base.BaseActivity
import com.efemoney.forecast.util.*
import kotlinx.android.synthetic.main.activity_today.*
import kotlinx.android.synthetic.main.appbar.*
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class TodayActivity : BaseActivity(), TodayMvp.View {

    @Inject lateinit var presenter: TodayPresenter

    val sdf = SimpleDateFormat("dd MMM, yyyy", Locale.getDefault())

    val RC_LOCATION = 1000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_today)

        setupToolbar()

        DaggerTodayComponent.builder()
                .repoComponent(application.repo)
                .todayModule(TodayModule(this))
                .build()
                .inject(this)
    }

    override fun onStart() {
        super.onStart()

        presenter.subscribe()
    }

    override fun onStop() {
        super.onStop()

        presenter.unsubscribe()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {

        val granted = PackageManager.PERMISSION_GRANTED

        if (requestCode == RC_LOCATION && permissions.size == 1)
            presenter.onLocationPermissionResult(grantResults[0] == granted)
    }


    override fun presenter(presenter: TodayMvp.Presenter) {
    }

    override fun checkLocationPermission() {

        val granted = checkPermissionFor(Manifest.permission.ACCESS_FINE_LOCATION)

        presenter.onLocationPermissionResult(granted)
    }

    override fun requestLocationPermission() {

        requestThesePermissions(RC_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun showLocationRationale() {

        val snack = Snackbar.make(search, "This app needs location permission to function", Snackbar.LENGTH_INDEFINITE)
        snack.setAction(R.string.grant, { requestLocationPermission() })
        snack.show()
    }

    override fun shouldShowLocationRationale(): Boolean {

        return shouldShowRationaleFor(Manifest.permission.ACCESS_FINE_LOCATION)
    }

    override fun showProgressIndicator(showIndicator: Boolean) {

        if (showIndicator) progress.visible() else progress.gone()

        if (showIndicator) updating.visible() else updating.gone()
        if (!showIndicator) updated_date.visible() else updated_date.gone()
    }

    override fun showUpdatedAtDate(date: Date) {

        updated_date.text = getString(R.string.format_last_updated, sdf.format(date))
    }

    override fun showCrudeLocation(location: Location) {

        text_location.text = getString(R.string.format_location, location.lng.toString(), location.lat.toString())
    }

    override fun showPrettyLocation(place: String?, countryCode: String?) {

        text_location.text = getString(R.string.format_location, place, countryCode)
    }

    override fun showWeatherIcon(iconUrl: String) {

        Glide.with(this)
                .load(iconUrl)
                .placeholder(R.drawable.curved_grey)
                .into(icon)
    }

    override fun showError(message: String) {

        Snackbar.make(search, message, Snackbar.LENGTH_LONG).show()
    }

    override fun showGenericError() {

        showError("Something went wrong")
    }

    override fun showWeatherDescription(description: String?) {

        desc.text = description
    }

    override fun showWeatherMinMaxTemp(min: String?, max: String?) {

        min_temp.text = getString(R.string.format_temp_min, min)
        max_temp.text = getString(R.string.format_temp_max, max)
    }


    private fun setupToolbar() {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayShowTitleEnabled(false)
        }
    }
}
