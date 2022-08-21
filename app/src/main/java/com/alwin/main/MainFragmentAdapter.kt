package com.alwin.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alwin.home.HomeFragment
import com.alwin.main.MainActivity.Companion.HOME_INDEX
import com.alwin.main.MainActivity.Companion.OFFICIAL_INDEX
import com.alwin.me.MeFragment
import com.alwin.official.OfficialFragment

class MainFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            HOME_INDEX -> HomeFragment()
            OFFICIAL_INDEX -> OfficialFragment()
            else -> MeFragment()
        }
    }
}