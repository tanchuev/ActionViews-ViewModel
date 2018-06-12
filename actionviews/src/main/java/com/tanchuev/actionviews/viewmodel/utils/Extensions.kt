package com.tanchuev.actionviews.viewmodel.utils

import kotlin.reflect.KProperty

/**
 * Created by tanchuev
 */

fun <T> mutableLazy(initializer: () -> T) = Delegate(lazy(initializer))

class Delegate<T>(private val lazy: Lazy<T>) {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return value ?: lazy.getValue(thisRef, property)
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
    }
}

fun Any?.isNullOrEmpty(): Boolean =
    when (this) {
    //todo add if(isEmptyResponse)
        null -> true
        is CharSequence -> length == 0
        is Collection<*> -> isEmpty()
        is Map<*, *> -> isEmpty()
        else -> false
    }