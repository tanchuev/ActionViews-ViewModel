package com.tanchuev.actionviews.viewmodel.activity

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.tanchuev.actionviews.viewmodel.R
import com.tanchuev.actionviews.viewmodel.utils.ErrorMessage
import com.tanchuev.actionviews.viewmodel.utils.findViewByIdNullable
import com.tanchuev.actionviews.viewmodel.utils.mutableLazy
import com.tanchuev.actionviews.viewmodel.view.EmptyContentView
import com.tanchuev.actionviews.viewmodel.view.ErrorView
import com.tanchuev.actionviews.viewmodel.view.LoadingView
import com.tanchuev.actionviews.viewmodel.view.NoInternetView
import com.tanchuev.actionviews.viewmodel.viewmodel.ActionsViewModel
import com.tanchuev.actionviews.viewmodel.widget.ToastView

/**
 * @author tanchuev
 */

abstract class ActionsActivity : AppCompatActivity() {

    open val viewModel: ActionsViewModel? = null

    var contentActionView: View by mutableLazy { findViewById<View>(R.id.contentView) }
    var loadingActionView: LoadingView? by mutableLazy { findViewByIdNullable<View>(R.id.loadingView) as LoadingView? }
    var noInternetActionView: NoInternetView? by mutableLazy { findViewByIdNullable<View>(R.id.noInternetView) as NoInternetView? }
    var emptyContentActionView: EmptyContentView? by mutableLazy { findViewByIdNullable<View>(R.id.emptyContentView) as EmptyContentView? }
    var errorActionView: ErrorView by mutableLazy { ToastView(this) }

    override fun onStart() {
        super.onStart()
        initViewModel()
    }

    fun initViewModel() {
        viewModel?.contentVisibility?.observe(this, Observer { })
        viewModel?.loadingVisibility?.observe(this, Observer {
            loadingActionView?.setVisibility(it!!, contentActionView)
        })
        viewModel?.noInternetVisibility?.observe(this, Observer {
            if (it!!) {
                if (noInternetActionView != null) {
                    noInternetActionView!!.setVisibility(it, contentActionView)
                } else {
                    errorActionView.showMessage(ErrorMessage(R.string.errorNoInternet))
                }
            } else {
                noInternetActionView?.setVisibility(it, contentActionView)
            }
        })
        viewModel?.emptyContentVisibility?.observe(this, Observer {
            if (it!!) {
                if (emptyContentActionView != null) {
                    emptyContentActionView!!.setVisibility(it, contentActionView)
                } else {
                    errorActionView.showMessage(ErrorMessage(R.string.errorEmptyContent))
                }
            } else {
                emptyContentActionView?.setVisibility(it, contentActionView)
            }
        })
        viewModel?.isContentEmpty?.observe(this, Observer {
            emptyContentActionView?.isContentEmpty = it!!
        })
        viewModel?.errorMessage?.observe(this, Observer {
            errorActionView.showMessage(it!!)
        })
    }
}