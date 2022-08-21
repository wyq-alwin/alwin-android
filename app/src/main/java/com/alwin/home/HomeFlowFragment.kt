package com.alwin.home

import android.os.Bundle
import android.view.View
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
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.flow.collectLatest

class HomeFlowFragment : Fragment(R.layout.fragment_home_flow) {

    private val binding: FragmentHomeFlowBinding by binding()
    private val homeFlowViewModel: HomeFlowViewModel by activityViewModels()
    private val homeFlowAdapter = HomeArticleAdapter()
    private val bannerAdapter = ImageAdapter()
    var loading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.banner.setAdapter(bannerAdapter)
            .addBannerLifecycleObserver(this).indicator = CircleIndicator(context)
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
                when (it.refresh) {
                    is LoadState.NotLoading -> {
                        loading = false
                        isRefreshing = false
                    }
                    is LoadState.Loading -> {
                        loading = true
                    }
                    is LoadState.Error -> {
                        loading = false
                        isRefreshing = false
                    }
                }
            }
        }
        lifecycleScope.launchWhenCreated {
            homeFlowViewModel.getBannerData()
        }
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
}