package com.example.nyarticles.esspressoTest

import androidx.test.espresso.idling.CountingIdlingResource

object EsspressoIdlingResource {
    private const val RESOURCE = "GLOBAL"
    @JvmField val countingIdlingResource:CountingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment(){
        countingIdlingResource.increment()
    }

    fun decrement(){
        if(!countingIdlingResource.isIdleNow)
            countingIdlingResource.decrement()
    }
}