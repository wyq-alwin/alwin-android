package com.alwin.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwin.android.R
import com.alwin.android.databinding.FragmentHomeFlowBinding
import com.alwin.home.viewmodel.HomeFlowViewModel
import com.alwin.util.binding
import com.alwin.util.dp2px
import com.alwin.widget.ImageAdapter
import com.alwin.widget.SpacesItemDecoration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class HomeFlowFragment : Fragment(R.layout.fragment_home_flow) {

    companion object {
        const val TAG = "HomeFlowFragment"
    }

    private val binding: FragmentHomeFlowBinding by binding()
    private val homeFlowViewModel: HomeFlowViewModel by activityViewModels()
    private val homeFlowAdapter = HomeArticleAdapter()
    private val bannerAdapter = ImageAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val params = binding.recyclerview.layoutParams as LinearLayout.LayoutParams
        // params.height =
        //     resources.displayMetrics.heightPixels - SystemUtil.getStatusBarHeight(requireActivity()) - 56.dp2px * 3
        // binding.recyclerview.layoutParams = params
        //
        // binding.banner.setAdapter(bannerAdapter)
        //     .addBannerLifecycleObserver(this).indicator = CircleIndicator(context)
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpacesItemDecoration(4.dp2px))
            adapter = homeFlowAdapter
            isNestedScrollingEnabled = false
        }
        binding.refreshLayout.apply {
            isRefreshing = true
            setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light
            )
            setProgressBackgroundColorSchemeResource(android.R.color.white);
            setOnRefreshListener {
                homeFlowAdapter.refresh()
            }
            homeFlowAdapter.addLoadStateListener {
                isRefreshing = when (it.refresh) {
                    is LoadState.NotLoading -> {
                        false
                    }
                    is LoadState.Loading -> {
                        true
                    }
                    is LoadState.Error -> {
                        false
                    }
                }
            }
        }
        // lifecycleScope.launchWhenCreated {
        //     homeFlowViewModel.getBannerData()
        // }
        lifecycleScope.launchWhenCreated {
            homeFlowViewModel.articleFlow.collectLatest {
                homeFlowAdapter.submitData(it)
            }
        }
        lifecycleScope.launchWhenCreated {
            homeFlowAdapter.loadStateFlow.collectLatest { state ->

            }
        }
        homeFlowViewModel.bannerModel.observe(viewLifecycleOwner) {
            bannerAdapter.setDatas(it)
        }
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }
}