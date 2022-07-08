package com.alwin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwin.android.databinding.FragmentHomeFlowBinding
import com.alwin.home.viewmodel.HomeFlowViewModel
import com.alwin.util.dp2px
import com.alwin.widget.ImageAdapter
import com.alwin.widget.SpacesItemDecoration
import com.youth.banner.indicator.CircleIndicator
import kotlinx.coroutines.flow.collectLatest

class HomeFlowFragment : Fragment() {

    private var binding: FragmentHomeFlowBinding? = null

    private val homeFlowViewModel: HomeFlowViewModel by activityViewModels()

    private val homeFlowAdapter = HomeArticleAdapter()
    private val bannerAdapter = ImageAdapter()
    private fun binding() = binding!!
    var loading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeFlowBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().banner.setAdapter(bannerAdapter)
            .addBannerLifecycleObserver(this).indicator = CircleIndicator(context)
        binding().recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpacesItemDecoration(4.dp2px))
            adapter = homeFlowAdapter
            isNestedScrollingEnabled = false
            focusable = 0
        }
        binding().refreshLayout.apply {
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

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}