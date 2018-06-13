package com.tanchuev.actionviews.viewmodel.rx.observer


import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableSingleObserver

/**
 * @author tanchuev
 */
class BaseSingleObserver<T> : DisposableSingleObserver<T> {

    private var onSuccess: Consumer<in T>? = null
    private var onError: Consumer<Throwable>? = null

    constructor(onSuccess: ((T) -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        if (onSuccess != null) {
            this.onSuccess = Consumer { onSuccess(it) }
        }
        if (onError != null) {
            this.onError = Consumer { onError(it) }
        }
    }

    override fun onSuccess(t: T) {
        try {
            onSuccess?.accept(t)
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
