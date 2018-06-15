package com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.viewmodel

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.tanchuev.actionviews.viewmodel.utils.clearSubscriptions
import com.tanchuev.actionviews.viewmodel.viewmodel.ActionsViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.EventBusException
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject
import ru.terrakok.cicerone.Router

abstract class BaseViewModel constructor(lifecycle: Lifecycle) : ActionsViewModel(),
    LifecycleObserver, KoinComponent {

    protected val router: Router by inject()

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    open fun onResume() {
        try {
            EventBus.getDefault().register(this)
        } catch (e: EventBusException) {
            e.printStackTrace()
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    open fun onPause() {
        onCleared()
    }

    override fun onCleared() {
        clearSubscriptions()
        try {
            EventBus.getDefault().unregister(this)
        } catch (e: EventBusException) {
            e.printStackTrace()
        }
    }
}