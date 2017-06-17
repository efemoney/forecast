package com.efemoney.forecast

import io.reactivex.Scheduler

interface SchedulerProvider {

    fun io(): Scheduler

    fun single(): Scheduler

    fun mainThread(): Scheduler

    fun trampoline(): Scheduler
}