package com.alwin.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.alwin.home.HomeFragment
import com.alwin.mine.MineFragment
import com.alwin.official.OfficialFragment

class HomeFragmentAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {
    
    companion object {

    }

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFlowFragment()
            else ->{
                MineFragment()
            }
        }
    }
}