package com.alwin.me

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.alwin.android.R
import com.alwin.android.databinding.FragmentMeBinding
import com.alwin.home.HomeArticleAdapter
import com.alwin.login.LoginActivity
import com.alwin.util.AccountUtils
import com.alwin.util.binding
import com.alwin.util.dp2px
import com.alwin.widget.SpacesItemDecoration
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MeFragment : Fragment(R.layout.fragment_me) {

    private val binding: FragmentMeBinding by binding()
    private val viewModel: MeViewModel by viewModels()
    private val followAdapter = HomeArticleAdapter()
    private var loading = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerview.apply {
            adapter = followAdapter
            layoutManager = LinearLayoutManager(context)
            addItemDecoration(SpacesItemDecoration(4.dp2px))
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
                followAdapter.refresh()
            }
            followAdapter.addLoadStateListener {
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
        viewModel.isLogin.observe(viewLifecycleOwner) { isLogin ->
            binding.text.let {
                if (isLogin) {
                    lifecycleScope.launch {
                        it.text = "用户名: ${AccountUtils.getUserName()}"
                    }
                    it.isEnabled = false
                    it.isClickable = false
                } else {
                    it.text = "去登陆"
                    it.setOnClickListener {
                        context?.startActivity(Intent(context, LoginActivity::class.java))
                    }
                }
            }
        }
        lifecycleScope.launch {
            viewModel.articleFlow.collectLatest {
                followAdapter.submitData(it)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            // todo 怎么自动更新
            viewModel.isLogin.postValue(AccountUtils.isLogin())
        }
    }
}