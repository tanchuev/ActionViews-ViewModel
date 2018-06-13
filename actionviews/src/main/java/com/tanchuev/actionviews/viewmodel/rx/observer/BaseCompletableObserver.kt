package com.tanchuev.actionviews.viewmodel.rx.observer


import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableCompletableObserver

/**
 * @author tanchuev
 */
class BaseCompletableObserver : DisposableCompletableObserver {

    private var onComplete: Action? = null
    private var onError: Consumer<Throwable>? = null

    constructor(onComplete: (() -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        if (onComplete != null) {
            this.onComplete = Action { onComplete() }
        }
        if (onError != null) {
            this.onError = Consumer { onError(it) }
        }
    }

    override fun onComplete() {
        try {
            onComplete?.run()
        } catch (e: Exception) {
            e.printStackTrace()
        }

        dispose()
    }

    override fun onError(e: Throwable) {
        try {
            onError?.accept(e)
        } catch (ex: Exception) {
            ex.printStackTrace()
        }

        dispose()
    }
}
