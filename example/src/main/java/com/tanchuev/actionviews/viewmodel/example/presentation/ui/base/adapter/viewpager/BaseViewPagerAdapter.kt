package com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.adapter.viewpager

import android.support.v4.view.PagerAdapter
import android.view.View
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.adapter.BaseAdapter
import java.util.*

/**
 * @author tanchuev
 */

abstract class BaseViewPagerAdapter<TItem> : PagerAdapter(), BaseAdapter<TItem> {

    protected var items: MutableList<TItem> = ArrayList()

    override fun add(item: TItem) {
        if (item in items) items[items.indexOf(item)] = item
        else items.add(item)
        notifyDataSetChanged()
    }

    override fun addAll(items: List<TItem>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun remove(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    override fun removeAll() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getCount(): Int = items.size

    abstract override fun isViewFromObject(view: View, any: Any): Boolean
}
