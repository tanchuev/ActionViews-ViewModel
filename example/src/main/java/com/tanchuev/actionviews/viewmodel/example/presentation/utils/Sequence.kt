package com.tanchuev.actionviews.viewmodel.example.presentation.utils

import java.util.concurrent.atomic.AtomicInteger

private val seed = AtomicInteger()

val nextInt: Int
    get() = seed.incrementAndGet()
