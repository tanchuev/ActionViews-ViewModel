package com.tanchuev.actionviews.viewmodel.example.presentation.ui.adapter.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import com.tanchuev.actionviews.viewmodel.example.domain.model.Gift
import com.tanchuev.actionviews.viewmodel.example.presentation.event.OnGiftClickEvent
import com.tanchuev.actionviews.viewmodel.utils.setImage
import kotlinx.android.synthetic.main.li_gift.view.*
import org.greenrobot.eventbus.EventBus

/**
 * Created by marat.taychinov
 */

class GiftsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var item: Gift

    fun bind(item: Gift) = itemView.apply {
        this@GiftsViewHolder.item = item

        name.text = item.name
        ivGift.setImage(item.imageUrl)
        like.text = item.rating.toString()
        like.setOnClickListener {}

        setOnClickListener { onItemViewClick() }
    }

    private fun onItemViewClick() = itemView.apply {
        EventBus.getDefault()
            .post(OnGiftClickEvent(item))
    }
}
