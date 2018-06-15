package com.tanchuev.actionviews.viewmodel.example.presentation.ui.gifts

import android.arch.lifecycle.Observer
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.tanchuev.actionviews.viewmodel.example.domain.model.Gift
import com.tanchuev.actionviews.viewmodel.example.presentation.R
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.adapter.AllGiftsAdapter
import com.tanchuev.actionviews.viewmodel.example.presentation.ui.base.fragment.BaseFragment
import org.koin.android.architecture.ext.viewModel
import kotlinx.android.synthetic.main.fr_gifts.*
import kotlinx.android.synthetic.main.fr_gifts.contentView as recyclerView

/**
 * @author tanchuev
 */

class AllGiftsFragment : BaseFragment(), android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener {

    companion object {
        fun newInstance(): AllGiftsFragment {
            return AllGiftsFragment()
        }
    }

    override val viewModel: AllGiftsViewModel by viewModel()
    override val layout: Int = R.layout.fr_gifts

    private val giftsObserver = Observer<List<Gift>> { gifts -> gifts?.let { adapter.addAll(gifts) } }
    private val clearObserver = Observer<Boolean> { clear -> clear?.let { if (clear) adapter.removeAll() } }

    private lateinit var adapter: AllGiftsAdapter

    override fun afterInitUI() {
        viewModel.gifts.observe(this, giftsObserver)
        viewModel.clearGifts.observe(this, clearObserver)

        adapter = AllGiftsAdapter()
        recyclerView.layoutManager = LinearLayoutManager(activity)
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(30)
        recyclerView.isDrawingCacheEnabled = true
        recyclerView.drawingCacheQuality = View.DRAWING_CACHE_QUALITY_HIGH
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        loadingView.setOnRefreshListener(this)
    }

    override fun onPause() {
        loadingView.setOnRefreshListener(null)
        super.onPause()
    }

    override fun onRefresh() {
        viewModel.getGifts(true)
    }
}
