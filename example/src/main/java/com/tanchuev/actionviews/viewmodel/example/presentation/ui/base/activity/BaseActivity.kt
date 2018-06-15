package com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.activity

import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatDelegate
import com.tanchuev.actionviews.viewmodel.activity.ActionsActivity
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Properties
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Screens
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.releaseContext
import org.koin.android.ext.android.setProperty
import org.koin.error.NoScopeFoundException
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import timber.log.Timber

/**
 * @author tanchuev
 */
abstract class BaseActivity : ActionsActivity() {

    init {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    }

    protected val router: Router by inject()
    private val navigator: Navigator by inject()
    private val navigatorHolder: NavigatorHolder by inject()

    protected open val startFragment: String? = null

    @get:LayoutRes
    protected abstract val layout: Int

    override fun onBackPressed() = router.finishChain()

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout)

        if (startFragment != null) {
            router.navigateTo(startFragment)
        }
    }

    override fun onStart() {
        setProperty(Properties.ACTIVITY, this)
        super.onStart()
    }

    override fun onResume() {
        setProperty(Properties.ACTIVITY, this)
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        try {
            releaseContext(Screens.BaseActivity)
            releaseContext(this::class.simpleName.toString())
        } catch (e: NoScopeFoundException) {
            Timber.i(e)
        }
        super.onDestroy()
    }
}
