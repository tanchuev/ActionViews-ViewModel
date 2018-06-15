package com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.adapter


/**
 * @author tanchuev
 */

interface BaseAdapter<in T> {

    fun add(item: T)

    fun addAll(items: List<T>)

    fun remove(position: Int)

    fun remove(id: String)

    fun removeAll()
}