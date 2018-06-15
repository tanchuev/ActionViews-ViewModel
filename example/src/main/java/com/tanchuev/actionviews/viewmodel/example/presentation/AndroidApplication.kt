package com.tanchuev.actionviews.viewmodel.example.presentation

import android.support.multidex.MultiDexApplication
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Properties
import com.tanchuev.actionviews.viewmodel.example.presentation.di.getAllModules
import org.koin.android.ext.android.startKoin
import timber.log.Timber

class AndroidApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String {
                    return String.format(
                        "[L:%s] [M:%s] [C:%s]",
                        element.lineNumber,
                        element.methodName,
                        super.createStackElementTag(element)
                    )
                }
            })
        }

        startKoin(
            this,
            getAllModules(),
            mapOf(Pair(Properties.APPLICATION_CONTEXT, this))
        )
    }
}
