package com.alwin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwin.android.databinding.FragmentHomeFlowBinding
import com.alwin.home.viewmodel.HomeFlowViewModel
import com.alwin.util.dp2px
import com.alwin.widget.ImageAdapter
import com.alwin.widget.SpacesItemDecoration
import com.youth.banner.indicator.CircleIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class HomeFlowFragment : Fragment() {

    private var binding: FragmentHomeFlowBinding? = null

    @Inject
    lateinit var homeFlowViewModel: HomeFlowViewModel

    private val homeFlowAdapter = HomeFlowArticleAdapter()
    private val bannerAdapter = ImageAdapter()
    private fun binding() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeFlowBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().recyclerview.apply {
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpacesItemDecoration(4.dp2px))
            adapter = homeFlowAdapter
            isNestedScrollingEnabled = false
            focusable = 0
        }
        binding().banner.setAdapter(bannerAdapter)
            .addBannerLifecycleObserver(this)
            .setIndicator(CircleIndicator(context))
        lifecycleScope.launch {
            homeFlowViewModel.getBannerData()
        }
        lifecycleScope.launch {
            homeFlowViewModel.articleFlow.collectLatest {
                homeFlowAdapter.submitData(it)
            }
        }
        lifecycleScope.launch {
            homeFlowAdapter.loadStateFlow.collectLatest { state ->

            }
        }
        homeFlowViewModel.bannerModel.observe(viewLifecycleOwner) {
            bannerAdapter.setDatas(it)
        }
        homeFlowAdapter.addLoadStateListener {

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}