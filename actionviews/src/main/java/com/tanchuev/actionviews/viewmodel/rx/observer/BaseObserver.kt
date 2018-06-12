package com.tanchuev.actionviews.viewmodel.rx.observer


import io.reactivex.functions.Action
import io.reactivex.functions.Consumer
import io.reactivex.observers.DisposableObserver

/**
 * @author tanchuev
 */
class BaseObserver<T> : DisposableObserver<T> {

    var onNext: Consumer<in T>? = null
        private set
    var onError: Consumer<Throwable>? = null
        private set
    var onComplete: Action? = null
        private set

    constructor(onNext: Consumer<in T>) {
        this.onNext = onNext
    }

    constructor(onComplete: Action) {
        this.onComplete = onComplete
    }

    constructor(onNext: Consumer<in T>, onComplete: Action) {
        this.onNext = onNext
        this.onComplete = onComplete
    }

    constructor(onNext: Consumer<in T>, onError: Consumer<Throwable>) {
        this.onNext = onNext
        this.onError = onError
    }

    constructor(onNext: Consumer<in T>?, onError: Consumer<Throwable>?, onComplete: Action?) {
        this.onNext = onNext
        this.onError = onError
        this.onComplete = onComplete
    }

    constructor(onNext: ((T) -> Unit)? = null, onComplete: (() -> Unit)? = null, onError: ((Throwable) -> Unit)? = null) {
        if (onNext != null) {
            this.onNext = Consumer { onNext(it) }
        }
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

    override fun onNext(t: T) {
        try {
            onNext?.accept(t)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }
}
