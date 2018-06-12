package com.tanchuev.actionviews.viewmodel.rx.observer


import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableCompletableObserver

/**
 * @author tanchuev
 */
class BaseCompletableObserver : DisposableCompletableObserver {

    var onComplete: Action? = null
        private set
    var onError: Consumer<Throwable>? = null
        private set

    constructor(onComplete: Action) {
        this.onComplete = onComplete
    }

    constructor(onComplete: Action?, onError: Consumer<Throwable>?) {
        this.onComplete = onComplete
        this.onError = onError
    }

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
