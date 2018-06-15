package com.tanchuev.actionviews.viewmodel.example.presentation.ui.gifts

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.OnLifecycleEvent
import com.tanchuev.actionviews.viewmodel.example.domain.model.Gift
import com.tanchuev.actionviews.viewmodel.example.domain.repository.GiftRepository
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Screens
import com.tanchuev.actionviews.viewmodel.example.presentation.event.LoadMoreEvent
import com.tanchuev.actionviews.viewmodel.example.presentation.event.OnGiftClickEvent
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.viewmodel.BaseViewModel
import com.tanchuev.actionviews.viewmodel.utils.execute
import com.tanchuev.actionviews.viewmodel.utils.withActionViews
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import java.util.*

/**
 * @author tanchuev
 */
class AllGiftsViewModel constructor(val giftRepository: GiftRepository, lifecycle: Lifecycle) : BaseViewModel(lifecycle) {

    companion object {
        const val LOAD_COUNT = 30
    }

    private var offset: Int = 0
    private var lastTimeUpdate: Long = 0

    val gifts = MutableLiveData<List<Gift>>()
    val clearGifts = MutableLiveData<Boolean>()

    fun getGifts(init: Boolean) {
        //todo add paging from arch components
        if (init) {
            clearGifts.value = true
        }

        giftRepository.getAll()
            .withActionViews(this)
            .execute({
                //gifts.value?.addAll(it)
                gifts.value = it
                lastTimeUpdate = Calendar.getInstance().timeInMillis
                isContentEmpty.postValue(gifts.value!!.isEmpty())
            })
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    override fun onResume() {
        if (lastTimeUpdate == 0L || Calendar.getInstance().timeInMillis - lastTimeUpdate > 600000) {
            getGifts(true)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onLoadMore(event: LoadMoreEvent) {
        getGifts(false)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onGiftClick(event: OnGiftClickEvent) {
        //router.navigateTo(Screens.Gift, event.gift)
    }
}