package com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.adapter.recyclerview

import android.support.v7.widget.RecyclerView
import com.tanchuev.actionviews.viewmodel.example.domain.model.base.Model
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.adapter.BaseAdapter
import java.util.*

/**
 * @author tanchuev
 */
abstract class BaseRecyclerAdapter<T : Model, TViewHolder : RecyclerView.ViewHolder> :
    RecyclerView.Adapter<TViewHolder>(), BaseAdapter<T> {

    protected var items: MutableList<T> = ArrayList()

    override fun add(item: T) {
        if (item in items) items[items.indexOf(item)] = item
        else items.add(item)
        notifyItemChanged(items.indexOf(item))
    }

    override fun addAll(items: List<T>) {
        this.items.addAll(items)
        notifyDataSetChanged()
    }

    override fun remove(position: Int) {
        items.removeAt(position)
        notifyDataSetChanged()
    }

    override fun remove(id: String) {
        for (item in items) {
            if (item.id == id) {
                items.remove(item)
                break
            }
        }
        notifyDataSetChanged()
    }

    override fun removeAll() {
        items.clear()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = items.size
}
