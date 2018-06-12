package com.tanchuev.actionviews.viewmodel.fragment

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import com.tanchuev.actionviews.viewmodel.R
import com.tanchuev.actionviews.viewmodel.utils.findViewById
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
abstract class ActionsFragment : Fragment() {

    abstract val viewModel: ActionsViewModel

    var contentActionView: View by mutableLazy { findViewById<View>(R.id.contentView) }
    var loadingActionView: LoadingView? by mutableLazy { findViewByIdNullable<View>(R.id.loadingView) as LoadingView? }
    var noInternetActionView: NoInternetView? by mutableLazy { findViewByIdNullable<View>(R.id.noInternetView) as NoInternetView? }
    var emptyContentActionView: EmptyContentView? by mutableLazy { findViewByIdNullable<View>(R.id.emptyContentView) as EmptyContentView? }
    var errorActionView: ErrorView by mutableLazy { ToastView(requireActivity()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    fun initViewModel() {
        viewModel.contentVisibility.observe(this, Observer { })
        viewModel.loadingVisibility.observe(this, Observer {
            loadingActionView?.setVisibility(it!!, contentActionView)
        })
        viewModel.noInternetVisibility.observe(this, Observer {
            if (it!!) {
                if (noInternetActionView != null) {
                    noInternetActionView!!.setVisibility(it, contentActionView)
                } else {
                    errorActionView.showError(R.string.errorNoInternet)
                }
            } else {
                noInternetActionView?.setVisibility(it, contentActionView)
            }
        })
        viewModel.emptyContentVisibility.observe(this, Observer {
            if (it!!) {
                if (emptyContentActionView != null) {
                    emptyContentActionView!!.setVisibility(it, contentActionView)
                } else {
                    errorActionView.showError(R.string.errorEmptyContent)
                }
            } else {
                emptyContentActionView?.setVisibility(it, contentActionView)
            }
        })
        viewModel.isContentEmpty.observe(this, Observer {
            emptyContentActionView?.isContentEmpty = it!!
        })
        viewModel.error.observe(this, Observer {
            errorActionView.showError(it!!)
        })
    }
}