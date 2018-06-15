package com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.fragment

import android.os.Build
import android.os.Bundle
import android.support.annotation.CallSuper
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.WindowManager
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Properties
import com.tanchuev.actionviews.viewmodel.example.presentation.di.Screens
import com.tanchuev.actionviews.viewmodel.fragment.ActionsFragment
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
abstract class BaseFragment : ActionsFragment() {

    protected val router: Router by inject()
    private val navigator: Navigator by inject()
    private val navigatorHolder: NavigatorHolder by inject()

    @get:LayoutRes
    protected abstract val layout: Int

    abstract fun afterInitUI()

    override fun onCreate(savedInstanceState: Bundle?) {
        setProperty(Properties.FRAGMENT, this)
        super.onCreate(savedInstanceState)
    }

    @CallSuper
    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) =
        inflater.inflate(layout, container, false)

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setStatusBarTranslucent()
        afterInitUI()
    }

    private fun setStatusBarTranslucent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            requireActivity().window.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onDestroy() {
        try {
            releaseContext(Screens.BaseFragment)
            releaseContext(this::class.simpleName.toString())
        } catch (e: NoScopeFoundException) {
            Timber.i(e)
        }
        super.onDestroy()
    }
}