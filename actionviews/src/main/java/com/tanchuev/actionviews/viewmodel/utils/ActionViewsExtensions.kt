package com.tanchuev.actionviews.viewmodel.utils

import android.arch.lifecycle.MutableLiveData
import com.tanchuev.actionviews.viewmodel.viewmodel.ActionsViewModel
import io.reactivex.*
import io.reactivex.Observable
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.*

/**
 * Created by tanchuev
 */

val NETWORK_EXCEPTIONS = listOf(
    UnknownHostException::class,
    SocketTimeoutException::class,
    ConnectException::class
)

// region WITH DO ON ACTION
fun <T> Observable<T>.withActionViews(
    viewModel: ActionsViewModel,
    loadingVisibility: MutableLiveData<Boolean> = viewModel.loadingVisibility,
    noInternetVisibility: MutableLiveData<Boolean> = viewModel.noInternetVisibility,
    emptyContentVisibility: MutableLiveData<Boolean> = viewModel.emptyContentVisibility,
    error: MutableLiveData<Throwable> = viewModel.error,
    doOnLoadStart: () -> Unit = { doOnLoadSubscribe(loadingVisibility) },
    doOnLoadEnd: () -> Unit = { doOnLoadComplete(loadingVisibility) },
    doOnStartNoInternet: () -> Unit = { doOnNoInternetSubscribe(noInternetVisibility) },
    doOnNoInternet: (Throwable) -> Unit = { doOnNoInternet(noInternetVisibility) },
    doOnStartEmptyContent: () -> Unit = { doOnEmptyContentSubscribe(emptyContentVisibility) },
    doOnEmptyContent: () -> Unit = { doOnEmptyContent(emptyContentVisibility) },
    doOnError: (Throwable) -> Unit = { doOnError(error, it) }
) =
    subscribeOnBackground()
        .observeOnMain()
        .withErrorView(
            doOnStartNoInternet,
            doOnNoInternet,
            doOnStartEmptyContent,
            doOnEmptyContent,
            doOnError
        ).withLoadingView(doOnLoadStart, doOnLoadEnd)

fun <T> Flowable<T>.withActionViews(
    viewModel: ActionsViewModel,
    loadingVisibility: MutableLiveData<Boolean> = viewModel.loadingVisibility,
    noInternetVisibility: MutableLiveData<Boolean> = viewModel.noInternetVisibility,
    emptyContentVisibility: MutableLiveData<Boolean> = viewModel.emptyContentVisibility,
    error: MutableLiveData<Throwable> = viewModel.error,
    doOnLoadStart: () -> Unit = { doOnLoadSubscribe(loadingVisibility) },
    doOnLoadEnd: () -> Unit = { doOnLoadComplete(loadingVisibility) },
    doOnStartNoInternet: () -> Unit = { doOnNoInternetSubscribe(noInternetVisibility) },
    doOnNoInternet: (Throwable) -> Unit = { doOnNoInternet(noInternetVisibility) },
    doOnStartEmptyContent: () -> Unit = { doOnEmptyContentSubscribe(emptyContentVisibility) },
    doOnEmptyContent: () -> Unit = { doOnEmptyContent(emptyContentVisibility) },
    doOnError: (Throwable) -> Unit = { doOnError(error, it) }
) = subscribeOnBackground()
    .observeOnMain()
    .withErrorView(
        doOnStartNoInternet,
        doOnNoInternet,
        doOnStartEmptyContent,
        doOnEmptyContent,
        doOnError
    ).withLoadingView(doOnLoadStart, doOnLoadEnd)

fun <T> Single<T>.withActionViews(
    viewModel: ActionsViewModel,
    loadingVisibility: MutableLiveData<Boolean> = viewModel.loadingVisibility,
    noInternetVisibility: MutableLiveData<Boolean> = viewModel.noInternetVisibility,
    emptyContentVisibility: MutableLiveData<Boolean> = viewModel.emptyContentVisibility,
    error: MutableLiveData<Throwable> = viewModel.error,
    doOnLoadStart: () -> Unit = { doOnLoadSubscribe(loadingVisibility) },
    doOnLoadEnd: () -> Unit = { doOnLoadComplete(loadingVisibility) },
    doOnStartNoInternet: () -> Unit = { doOnNoInternetSubscribe(noInternetVisibility) },
    doOnNoInternet: (Throwable) -> Unit = { doOnNoInternet(noInternetVisibility) },
    doOnStartEmptyContent: () -> Unit = { doOnEmptyContentSubscribe(emptyContentVisibility) },
    doOnEmptyContent: () -> Unit = { doOnEmptyContent(emptyContentVisibility) },
    doOnError: (Throwable) -> Unit = { doOnError(error, it) }
) = subscribeOnBackground()
    .observeOnMain()
    .withErrorView(
        doOnStartNoInternet,
        doOnNoInternet,
        doOnStartEmptyContent,
        doOnEmptyContent,
        doOnError
    ).withLoadingView(doOnLoadStart, doOnLoadEnd)

fun <T> Maybe<T>.withActionViews(
    viewModel: ActionsViewModel,
    loadingVisibility: MutableLiveData<Boolean> = viewModel.loadingVisibility,
    noInternetVisibility: MutableLiveData<Boolean> = viewModel.noInternetVisibility,
    emptyContentVisibility: MutableLiveData<Boolean> = viewModel.emptyContentVisibility,
    error: MutableLiveData<Throwable> = viewModel.error,
    doOnLoadStart: () -> Unit = { doOnLoadSubscribe(loadingVisibility) },
    doOnLoadEnd: () -> Unit = { doOnLoadComplete(loadingVisibility) },
    doOnStartNoInternet: () -> Unit = { doOnNoInternetSubscribe(noInternetVisibility) },
    doOnNoInternet: (Throwable) -> Unit = { doOnNoInternet(noInternetVisibility) },
    doOnStartEmptyContent: () -> Unit = { doOnEmptyContentSubscribe(emptyContentVisibility) },
    doOnEmptyContent: () -> Unit = { doOnEmptyContent(emptyContentVisibility) },
    doOnError: (Throwable) -> Unit = { doOnError(error, it) }
) = subscribeOnBackground()
    .observeOnMain()
    .withErrorView(
        doOnStartNoInternet,
        doOnNoInternet,
        doOnStartEmptyContent,
        doOnEmptyContent,
        doOnError
    ).withLoadingView(doOnLoadStart, doOnLoadEnd)

fun Completable.withActionViews(
    viewModel: ActionsViewModel,
    loadingVisibility: MutableLiveData<Boolean> = viewModel.loadingVisibility,
    noInternetVisibility: MutableLiveData<Boolean> = viewModel.noInternetVisibility,
    emptyContentVisibility: MutableLiveData<Boolean> = viewModel.emptyContentVisibility,
    error: MutableLiveData<Throwable> = viewModel.error,
    doOnLoadStart: () -> Unit = { doOnLoadSubscribe(loadingVisibility) },
    doOnLoadEnd: () -> Unit = { doOnLoadComplete(loadingVisibility) },
    doOnStartNoInternet: () -> Unit = { doOnNoInternetSubscribe(noInternetVisibility) },
    doOnNoInternet: (Throwable) -> Unit = { doOnNoInternet(noInternetVisibility) },
    doOnStartEmptyContent: () -> Unit = { doOnEmptyContentSubscribe(emptyContentVisibility) },
    doOnEmptyContent: () -> Unit = { doOnEmptyContent(emptyContentVisibility) },
    doOnError: (Throwable) -> Unit = { doOnError(error, it) }
) = subscribeOnBackground()
    .observeOnMain()
    .withErrorView(
        doOnStartNoInternet,
        doOnNoInternet,
        doOnStartEmptyContent,
        doOnEmptyContent,
        doOnError
    ).withLoadingView(doOnLoadStart, doOnLoadEnd)
// endregion WITH DO ON ACTIONS

// region DO ON LOAD
fun <T> Observable<T>.withLoadingView(
    doOnLoadStart: () -> Unit,
    doOnLoadEnd: () -> Unit
): Observable<T> =
    doOnSubscribe { doOnLoadStart() }
        .doFinally { doOnLoadEnd() }

fun <T> Flowable<T>.withLoadingView(
    doOnLoadStart: () -> Unit,
    doOnLoadEnd: () -> Unit
): Flowable<T> =
    doOnSubscribe { doOnLoadStart() }
        .doFinally { doOnLoadEnd() }

fun <T> Single<T>.withLoadingView(
    doOnLoadStart: () -> Unit,
    doOnLoadEnd: () -> Unit
): Single<T> =
    doOnSubscribe { doOnLoadStart() }
        .doFinally { doOnLoadEnd() }

fun <T> Maybe<T>.withLoadingView(
    doOnLoadStart: () -> Unit,
    doOnLoadEnd: () -> Unit
): Maybe<T> =
    doOnSubscribe { doOnLoadStart() }
        .doFinally { doOnLoadEnd() }

fun Completable.withLoadingView(
    doOnLoadStart: () -> Unit,
    doOnLoadEnd: () -> Unit
): Completable =
    doOnSubscribe { doOnLoadStart() }
        .doFinally { doOnLoadEnd() }

private fun doOnLoadSubscribe(loadingVisibility: MutableLiveData<Boolean>) {
    loadingVisibility.value = true
}

private fun doOnLoadComplete(loadingVisibility: MutableLiveData<Boolean>) {
    loadingVisibility.value = false
}
// endregion DO ON LOAD

// region DO ON ERROR
fun <T> Observable<T>.withErrorView(
    doOnStartNoInternet: () -> Unit,
    doOnNoInternet: (Throwable) -> Unit,
    doOnStartEmptyContent: () -> Unit,
    doOnEmptyContent: () -> Unit,
    doOnError: (Throwable) -> Unit
): Observable<T> =
    doOnSubscribe {
        doOnStartNoInternet()
        doOnStartEmptyContent()
    }.flatMap {
        if (it.isNullOrEmpty()) {
            emptyObservable(doOnEmptyContent)
        } else this
    }.doOnError {
        when {
            it::class in NETWORK_EXCEPTIONS -> {
                doOnNoInternet(it)
            }
            it is NoSuchElementException -> {
                doOnEmptyContent()
            }
            else -> doOnError(it)
        }
    }

fun <T> Flowable<T>.withErrorView(
    doOnStartNoInternet: () -> Unit,
    doOnNoInternet: (Throwable) -> Unit,
    doOnStartEmptyContent: () -> Unit,
    doOnEmptyContent: () -> Unit,
    doOnError: (Throwable) -> Unit
): Flowable<T> =
    doOnSubscribe {
        doOnStartNoInternet()
        doOnStartEmptyContent()
    }.flatMap {
        if (it.isNullOrEmpty()) {
            emptyFlowable(doOnEmptyContent)
        } else this
    }.doOnError {
        when {
            it::class in NETWORK_EXCEPTIONS -> {
                doOnNoInternet(it)
            }
            it is NoSuchElementException -> {
                doOnEmptyContent()
            }
            else -> doOnError(it)
        }
    }

fun <T> Single<T>.withErrorView(
    doOnStartNoInternet: () -> Unit,
    doOnNoInternet: (Throwable) -> Unit,
    doOnStartEmptyContent: () -> Unit,
    doOnEmptyContent: () -> Unit,
    doOnError: (Throwable) -> Unit
): Single<T> =
    doOnSubscribe {
        doOnStartNoInternet()
        doOnStartEmptyContent()
    }
        .doOnError {
            when {
                it::class in NETWORK_EXCEPTIONS -> {
                    doOnNoInternet(it)
                }
                it is NoSuchElementException -> {
                    doOnEmptyContent()
                }
                else -> doOnError(it)
            }
        }
        .flatMap {
            if (it.isNullOrEmpty()) {
                throw NoSuchElementException()
            } else this
        }

fun <T> Maybe<T>.withErrorView(
    doOnStartNoInternet: () -> Unit,
    doOnNoInternet: (Throwable) -> Unit,
    doOnStartEmptyContent: () -> Unit,
    doOnEmptyContent: () -> Unit,
    doOnError: (Throwable) -> Unit
): Maybe<T> =
    doOnSubscribe {
        doOnStartNoInternet()
        doOnStartEmptyContent()
    }
        .doOnError {
            when {
                it::class in NETWORK_EXCEPTIONS -> {
                    doOnNoInternet(it)
                }
                it is NoSuchElementException -> {
                    doOnEmptyContent()
                }
                else -> doOnError(it)
            }
        }
        .flatMap {
            if (it.isNullOrEmpty()) {
                throw NoSuchElementException()
            } else this
        }

fun Completable.withErrorView(
    doOnStartNoInternet: () -> Unit,
    doOnNoInternet: (Throwable) -> Unit,
    doOnStartEmptyContent: () -> Unit,
    doOnEmptyContent: () -> Unit,
    doOnError: (Throwable) -> Unit
): Completable =
    doOnSubscribe {
        doOnStartNoInternet()
        doOnStartEmptyContent()
    }.doOnError {
        when {
            it::class in NETWORK_EXCEPTIONS -> {
                doOnNoInternet(it)
            }
            it is NoSuchElementException -> {
                doOnEmptyContent()
            }
            else -> doOnError(it)
        }
    }

private fun doOnError(
    error: MutableLiveData<Throwable>,
    t: Throwable
) {
    error.value = t
}
// endregion DO ON ERROR

// region DO ON NO INTERNET
private fun doOnNoInternetSubscribe(noInternetVisibility: MutableLiveData<Boolean>) {
    noInternetVisibility.value = false
}

private fun doOnNoInternet(noInternetVisibility: MutableLiveData<Boolean>) {
    noInternetVisibility.value = true
}
// endregion DO ON NO INTERNET

// region DO ON EMPTY CONTENT
private fun doOnEmptyContentSubscribe(emptyContentVisibility: MutableLiveData<Boolean>) {
    emptyContentVisibility.value = false
}

private fun doOnEmptyContent(emptyContentVisibility: MutableLiveData<Boolean>) {
    emptyContentVisibility.value = true
}

private fun <T> emptyObservable(doOnEmptyContent: () -> Unit): Observable<T> =
    Observable.create(ObservableOnSubscribe<T> { it.onComplete() })
        .doFinally { doOnEmptyContent() }

private fun <T> emptyFlowable(doOnEmptyContent: () -> Unit): Flowable<T> =
    Flowable.create(FlowableOnSubscribe<T> { it.onComplete() }, BackpressureStrategy.BUFFER)
        .doFinally { doOnEmptyContent() }
// endregion DO ON EMPTY CONTENT