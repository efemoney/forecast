package com.efemoney.forecast.ui.base

interface BaseView<in T: BasePresenter> {

    fun presenter(presenter: T)
}