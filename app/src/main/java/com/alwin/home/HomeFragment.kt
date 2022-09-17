package com.alwin.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alwin.android.R
import com.alwin.android.databinding.FragmentHomeBinding
import com.alwin.util.binding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 首页HomeFragment
 * 包含两个子Fragment：HomeFlowFragment 和
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    companion object {
        const val TAG = "HomeFragment"
    }

    private val titles = arrayOf("首页", "每日一问")
    private val binding: FragmentHomeBinding by binding()

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
        binding.contentPager.adapter = HomeFragmentAdapter(this)
        binding.contentPager.offscreenPageLimit = 1

        TabLayoutMediator(binding.tabLayout, binding.contentPager) { tab, position ->
            tab.text = "${titles[position]}"
        }.attach()
        Log.d(TAG, "onViewCreated")
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