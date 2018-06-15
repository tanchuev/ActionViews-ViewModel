package com.tanchuev.actionviews.viewmodel.example.presentation.di

import android.content.Context
import android.content.Intent
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.tanchuev.actionviews.viewmodel.example.data.repository.mock.GiftMockRepository
import com.tanchuev.actionviews.viewmodel.example.domain.repository.GiftRepository
import com.tanchuev.actionviews.viewmodel.example.presentation.BuildConfig
import com.tanchuev.actionviews.viewmodel.example.presentation.R
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Properties.ACTIVITY
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Properties.FRAGMENT
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.activity.BaseActivity
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.fragment.BaseFragment
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.gifts.AllGiftsFragment
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.gifts.AllGiftsViewModel
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.main.MainActivity
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.architecture.ext.viewModel
import org.koin.dsl.module.applicationContext
import org.koin.standalone.KoinComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.SupportAppNavigator


/**
 * Created by marat.taychinov
 *
 * В принципе все просто
 * делаем модули, это можно сказать те же модули, что и в даггере
 * если нужно, какие-то зависимости(которые тут называются компонентами) помечаем scope(тут это называется context)
 * чтобы передать что-то в модуль можно использовать properties
 * таким образом можно провайдить контекст андроидный
 * потом на уровне приложения запускаем наши модули через startKoin(modules, properties)
 * ну и запускаем модули через startKoin в тех местах где нам нужны какие-то зависимости отличные от аппликейшена
 * через resolveContext(String) убиваем наш scope, воскресает он сам
 * так же есть named зависимости
 * и ещё есть возможность контролировать каким образом создается зависимость, каждый раз новый инстанс или синглтон
 * вот в принципе и все)
 * большего для счастья и не надо
 */

//region applicationModule
val applicationModule = applicationContext {
    bean {
        get<Context>().getSharedPreferences(
            Properties.SHARED_PREFERENCES_KEY,
            Context.MODE_PRIVATE
        )
    }
    bean { okhttpClient() }
    bean { gson() }
    bean { retrofit(get(), get()) }
    bean { Cicerone.create() }
    bean { get<Cicerone<Router>>().navigatorHolder }
    bean { get<Cicerone<Router>>().router }
}

private fun retrofit(client: OkHttpClient, gson: Gson): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BuildConfig.SERVER_URL)
        // need for interceptors
        .client(client)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
}

private fun gson(): Gson {
    return GsonBuilder()
        //.excludeFieldsWithoutExposeAnnotation()
        // can not serialize nulls because it will affect optional fields in requests which should not be serialized
        // and we use null to not serialize them
        //.serializeNulls()
        // type adapters
        //.registerTypeAdapter(NewsItem.class, new NewsDeserializer())
        //.setDateFormat("yyyy-MM-dd'T'HH:mm:ss'.'SSS'Z'")
        .create()
}

private fun okhttpClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()
    if (!BuildConfig.BUILD_TYPE.contains("release")) {
        builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
    }
    return builder.build()
}
//endregion applicationModule

//region repositoryModule
val repositoryModule = applicationContext {
    bean { GiftMockRepository() as GiftRepository }
}
//endregion repositoryModule

//region activityModule
val activityModule = applicationContext {
    context(Screens.BaseActivity) {
        factory { getProperty(ACTIVITY) as BaseActivity }
        factory { getProperty(ACTIVITY) as AppCompatActivity }
        factory { get<BaseActivity>().lifecycle }
        factory { navigator(get()) }

        context(Screens.Main) {
            //viewModel { MainViewModel(get()) }
        }
    }
}

fun navigator(activity: AppCompatActivity): Navigator {
    return object : SupportAppNavigator(activity, R.id.fragmentContainer),
        KoinComponent {
        /**
         * navigateTo() – переход на новый экран.
         * newScreenChain() – сброс цепочки до корневого экрана и открытие одного нового.
         * newRootScreen() – сброс цепочки и замена корневого экрана.
         * replaceScreen() – замена текущего экрана.
         * backTo() – возврат на любой экран в цепочке.
         * exit() – выход с экрана.
         * exitWithMessage() – выход с экрана + отображение сообщения.
         * showSystemMessage() – отображение системного сообщения.
         */
        override fun createActivityIntent(
            context: Context?,
            screenKey: String?,
            data: Any?
        ): Intent? =
            when (screenKey) {
                Screens.Main -> Intent(context, MainActivity::class.java)
                else -> null
            }

        override fun createFragment(screenKey: String?, data: Any?): Fragment? =
            when (screenKey) {
                Screens.AllGifts -> AllGiftsFragment.newInstance()
                else -> null
            }
    }
}
//endregion activityModule

//region fragmentModule
val fragmentModule = applicationContext {
    context(Screens.BaseFragment) {
        factory { getProperty(FRAGMENT) as BaseFragment }
        factory { get<BaseFragment>().lifecycle }

        context(Screens.AllGifts) {
            viewModel { AllGiftsViewModel(get(), get()) }
        }
    }
}
//endregion fragmentModule

fun getAllModules() =
    listOf(
        applicationModule,
        repositoryModule,
        activityModule,
        fragmentModule
    )

/**
 * Module constants and screens names
 */
object Screens {
    const val Application = "Application"
    const val BaseActivity = "BaseActivity"
    const val BaseFragment = "BaseFragment"
    const val Main = "Main"

    const val AllGifts = "AllGifts"
}

object Properties {
    //region android
    const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"
    const val ACTIVITY = "ACTIVITY"
    const val FRAGMENT = "FRAGMENT"
    //endregion android

    //region shared preferences
    const val SHARED_PREFERENCES_KEY = "tanchuev.giftfinder"
    //endregion shared preferences

    //region bundle args
    //endregion bundle args
}

object Named {
}