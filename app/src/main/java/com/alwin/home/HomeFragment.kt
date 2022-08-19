package com.alwin.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alwin.android.R
import com.alwin.android.databinding.FragmentHomeBinding
import com.alwin.util.binding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 首页HomeFragment
 * 包含两个子Fragment：HomeFlowFragment 和
 */
class HomeFragment : Fragment(R.layout.fragment_home) {

    private val titles = arrayOf("首页", "每日一问")
    private val binding: FragmentHomeBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.contentPager.adapter = HomeFragmentAdapter(this)
        TabLayoutMediator(binding.tabLayout, binding.contentPager) { tab, position ->
            tab.text = "${titles[position]}"
        }.attach()
    }

    private val changeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //可以来设置选中时tab的大小
        }
    }
}