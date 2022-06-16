package com.alwin.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class MainFragmentAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    
    companion object {
        const val  HOME_INDEX = 0
        const val  OFFICIAL_INDEX = 1
        const val  MINE_INDEX = 2
    }

    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> HomeFragment()
            1 -> OfficialFragment()
            else -> MineFragment()
        }
    }
}