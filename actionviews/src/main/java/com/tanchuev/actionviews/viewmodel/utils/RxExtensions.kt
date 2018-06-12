package com.tanchuev.actionviews.viewmodel.utils

import com.tanchuev.actionviews.viewmodel.rx.observer.*
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by tanchuev
 */

private val compositeDisposable = CompositeDisposable()

fun clearSubscriptions() {
    compositeDisposable.clear()
}

fun unsubscribeAll() {
    compositeDisposable.dispose()
}

fun isNullOrDisposed(disposable: Disposable?) =
    disposable == null || disposable.isDisposed

fun dispose(subscription: Disposable?) {
    if (subscription != null && !subscription.isDisposed) subscription.dispose()
}

// region DO ON BACKGROUND
fun <T> Observable<T>.subscribeOnBackground() = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.subscribeOnBackground() = subscribeOn(Schedulers.io())

fun <T> Single<T>.subscribeOnBackground() = subscribeOn(Schedulers.io())

fun <T> Maybe<T>.subscribeOnBackground() = subscribeOn(Schedulers.io())

fun Completable.subscribeOnBackground() = subscribeOn(Schedulers.io())
// endregion DO ON BACKGROUND

// region DO ON MAIN
fun <T> Observable<T>.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())

fun Completable.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())
// endregion DO ON BACKGROUND

// region RESULT ON BACKGROUND
fun <T> Observable<T>.observeOnBackground() = observeOn(Schedulers.io())

fun <T> Flowable<T>.observeOnBackground() = observeOn(Schedulers.io())

fun <T> Single<T>.observeOnBackground() = observeOn(Schedulers.io())

fun <T> Maybe<T>.observeOnBackground() = observeOn(Schedulers.io())

fun Completable.observeOnBackground() = observeOn(Schedulers.io())
// endregion DO ON BACKGROUND

// region RESULT ON MAIN
fun <T> Observable<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

fun Completable.observeOnMain() = observeOn(AndroidSchedulers.mainThread())
// endregion DO ON MAIN

// region EXECUTE
fun <T> Observable<T>.execute(
    onNext: ((T) -> Unit)? = null,
    onComplete: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
): Observable<T> {
    val observer = BaseObserver(onNext, onComplete, onError)
    compositeDisposable.add(observer)
    subscribe(observer)
    return this
}

fun <T> Flowable<T>.execute(
    onNext: ((T) -> Unit)? = null,
    onComplete: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
): Flowable<T> {
    val subscriber = BaseSubscriber(onNext, onComplete, onError)
    compositeDisposable.add(subscriber)
    subscribe(subscriber)
    return this
}

fun <T> Single<T>.execute(
    onComplete: ((T) -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
): Single<T> {
    val observer = BaseSingleObserver(onComplete, onError)
    compositeDisposable.add(observer)
    subscribe(observer)
    return this
}

fun <T> Maybe<T>.execute(
    onComplete: ((T) -> Unit)? = null,
    onSuccess: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
): Maybe<T> {
    val observer = BaseMaybeObserver(onComplete, onSuccess, onError)
    compositeDisposable.add(observer)
    subscribe(observer)
    return this
}

fun Completable.execute(
    onComplete: (() -> Unit)? = null,
    onError: ((Throwable) -> Unit)? = null
): Completable {
    val observer = BaseCompletableObserver(onComplete, onError)
    compositeDisposable.add(observer)
    subscribe(observer)
    return this
}
// endregion EXECUTE

fun <T> Observable<T>.composeIf(condition: Boolean, transformer: ObservableTransformer<T, T>): Observable<T> =
    if (condition) compose(transformer)
    else this

fun <T> Flowable<T>.composeIf(condition: Boolean, transformer: FlowableTransformer<T, T>): Flowable<T> =
    if (condition) compose(transformer)
    else this

fun <T> Single<T>.composeIf(condition: Boolean, transformer: SingleTransformer<T, T>): Single<T> =
    if (condition) compose(transformer)
    else this

fun <T> Maybe<T>.composeIf(condition: Boolean, transformer: MaybeTransformer<T, T>): Maybe<T> =
    if (condition) compose(transformer)
    else this

fun Completable.composeIf(condition: Boolean, transformer: CompletableTransformer): Completable =
    if (condition) compose(transformer)
    else this