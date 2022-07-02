package com.alwin.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.alwin.android.databinding.FragmentMainBinding
import com.google.android.material.tabs.TabLayoutMediator

/**
 * 首页HomeFragment
 * 包含两个子Fragment：HomeFlowFragment 和
 */
class HomeFragment : Fragment() {

    private val titles = arrayOf("首页", "每日一问")
    private var binding: FragmentMainBinding? = null
    private fun binding() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding().contentPager.adapter = HomeFragmentAdapter(this)
        TabLayoutMediator(binding().tabLayout, binding().contentPager) { tab, position ->
            tab.text = "${titles[position]}"
        }.attach()
    }

    private val  changeCallback = object: ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            //可以来设置选中时tab的大小
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}