package com.tanchuev.actionviews.viewmodel.rx.observer


import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableSingleObserver

/**
 * @author tanchuev
 */
class BaseSingleObserver<T> : DisposableSingleObserver<T> {

    var onSuccess: Consumer<in T>? = null
        private set
    var onError: Consumer<Throwable>? = null
        private set

    constructor(onSuccess: Consumer<in T>) {
        this.onSuccess = onSuccess
    }

    constructor(onSuccess: Consumer<in T>?, onError: Consumer<Throwable>?) {
        this.onSuccess = onSuccess
        this.onError = onError
    }

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
