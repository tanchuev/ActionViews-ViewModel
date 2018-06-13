package com.tanchuev.actionviews.viewmodel.rx.observer


import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableMaybeObserver

/**
 * @author tanchuev
 */
class BaseMaybeObserver<T> : DisposableMaybeObserver<T> {

    private var onSuccess: Consumer<in T>? = null
    private var onComplete: Action? = null
    private var onError: Consumer<Throwable>? = null

    constructor(onSuccess: ((T) -> Unit)? = null, onComplete: (() -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        if (onSuccess != null) {
            this.onSuccess = Consumer { onSuccess(it) }
        }
        if (onComplete != null) {
            this.onComplete = Action { onComplete() }
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
