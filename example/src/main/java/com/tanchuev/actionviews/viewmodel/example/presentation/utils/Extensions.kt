package com.tanchuev.actionviews.viewmodel.example.presentation.utils

import android.content.ComponentCallbacks
import org.koin.android.ext.android.setProperty
import org.koin.error.MissingPropertyException
import org.koin.standalone.KoinComponent
import org.koin.standalone.getProperty
import org.koin.standalone.setProperty
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by marat.taychinov
 */
private val dateFormatWithTime: SimpleDateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")

val stringDateWithTime: String
    get() = dateFormatWithTime.format(Calendar.getInstance().time)

fun Any?.isNullOrEmpty(): Boolean =
    when (this) {
    //todo add if(isEmptyResponse)
        null -> true
        is CharSequence -> length == 0
        is Collection<*> -> isEmpty()
        is Map<*, *> -> isEmpty()
        else -> false
    }

fun <T, I : MutableList<T>> I.addIfNotNull(t: T?): I {
    if (t != null) {
        add(t)
    }

    return this
}

fun <T, I : MutableList<T>> I.addIf(condition: Boolean, t: T): I {
    if (condition) {
        add(t)
    }

    return this
}

fun <T, I : MutableList<T>> I.addIf(predicate: (T) -> Boolean, t: T): I {
    if (predicate(t)) {
        add(t)
    }

    return this
}

fun <K, V, M : MutableMap<K, V>> M.putIfNotNull(key: K, value: V?): M {
    if (value != null) {
        put(key, value)
    }

    return this
}

//region KOIN
fun <T> KoinComponent.setPropertyIfNotNull(key: String, t: T?) {
    if (t != null) {
        setProperty(key, t)
    }
}

fun <T> ComponentCallbacks.setPropertyIfNotNull(key: String, t: T?) {
    if (t != null) {
        setProperty(key, t)
    }
}

inline fun <reified T> KoinComponent.getPropertyNullable(key: String): T? {
    return try {
        getProperty(key)
    } catch (e: MissingPropertyException) {
        null
    }
}
//endregion KOIN

//region ANDROID
//endregion ANDROID