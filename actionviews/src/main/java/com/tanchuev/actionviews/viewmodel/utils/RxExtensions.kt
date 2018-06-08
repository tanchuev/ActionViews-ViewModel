package com.tanchuev.actionviews.viewmodel.utils

import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import com.tanchuev.actionviews.viewmodel.rx.observer.BaseCompletableObserver
import com.tanchuev.actionviews.viewmodel.rx.observer.BaseObserver
import com.tanchuev.actionviews.viewmodel.rx.observer.BaseSingleObserver
import com.tanchuev.actionviews.viewmodel.rx.observer.BaseSubscriber

/**
 * Created by marat.taychinov
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

/*inline infix fun <T> Any.deferObservable(crossinline start: () -> Observable<T>): Observable<T> = Observable.defer { start() }

inline infix fun <T> Any.deferFlowable(crossinline start: () -> Flowable<T>): Flowable<T> = Flowable.defer { start() }

inline infix fun <T> Any.deferSingle(crossinline start: () -> Single<T>): Single<T> = Single.defer { start() }

inline infix fun Any.deferCompletable(crossinline start: () -> Completable): Completable = Completable.defer { start() }*/

// region DO ON BACKGROUND
fun <T> Observable<T>.subscribeOnBackground() = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.subscribeOnBackground() = subscribeOn(Schedulers.io())

fun <T> Single<T>.subscribeOnBackground() = subscribeOn(Schedulers.io())

fun Completable.subscribeOnBackground() = subscribeOn(Schedulers.io())
// endregion DO ON BACKGROUND

// region DO ON MAIN
fun <T> Observable<T>.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())

fun Completable.subscribeOnMain() = subscribeOn(AndroidSchedulers.mainThread())
// endregion DO ON BACKGROUND

// region RESULT ON BACKGROUND
fun <T> Observable<T>.observeOnBackground() = observeOn(Schedulers.io())

fun <T> Flowable<T>.observeOnBackground() = observeOn(Schedulers.io())

fun <T> Single<T>.observeOnBackground() = observeOn(Schedulers.io())

fun Completable.observeOnBackground() = observeOn(Schedulers.io())
// endregion DO ON BACKGROUND

// region RESULT ON MAIN
fun <T> Observable<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.observeOnMain() = observeOn(AndroidSchedulers.mainThread())

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

fun Completable.composeIf(condition: Boolean, transformer: CompletableTransformer): Completable =
    if (condition) compose(transformer)
    else this