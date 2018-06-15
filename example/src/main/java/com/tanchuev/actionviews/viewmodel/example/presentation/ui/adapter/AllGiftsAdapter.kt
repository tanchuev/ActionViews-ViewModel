package com.tanchuev.actionviews.viewmodel.example.presentation.ui.adapter

import android.view.ViewGroup
import com.tanchuev.actionviews.viewmodel.example.domain.model.Gift
import com.tanchuev.actionviews.viewmodel.example.presentation.R
import com.tanchuev.actionviews.viewmodel.example.presentation.event.LoadMoreEvent
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.adapter.viewholder.GiftsViewHolder
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.adapter.recyclerview.BaseRecyclerAdapter
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.gifts.AllGiftsViewModel
import com.tanchuev.actionviews.viewmodel.utils.inflate
import org.greenrobot.eventbus.EventBus

/**
 * Created by marat.taychinov
 */

class AllGiftsAdapter : BaseRecyclerAdapter<Gift, GiftsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GiftsViewHolder {
        return GiftsViewHolder(parent.inflate(R.layout.li_gift))
    }

    override fun onBindViewHolder(holder: GiftsViewHolder, position: Int) {
        if (position == itemCount - AllGiftsViewModel.LOAD_COUNT / 2) {
            EventBus.getDefault().post(LoadMoreEvent())
        }

        val item = items[position]

        holder.bind(item)
    }
}
