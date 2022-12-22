package com.example.ArfanUAS.util

import androidx.test.espresso.idling.CountingIdlingResource

// menyediakan kelas penampung tempat Anda dapat menempatkan sumber daya nonaktif aplikasi Anda
object EssIdlingResources {
    private const val RESOURCE = "GLOBAL"
    val idlingRes = CountingIdlingResource(RESOURCE)

    fun increment() {
        idlingRes.increment()
    }

    fun decrement() {
        idlingRes.decrement()
    }
}