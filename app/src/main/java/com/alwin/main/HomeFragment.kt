package com.alwin.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alwin.android.databinding.FragmentMainBinding

/**
 * 首页HomeFragment
 * 包含两个子Fragment：HomeFlowFragment 和
 */
class HomeFragment : Fragment() {

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

    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}