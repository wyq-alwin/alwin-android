package com.alwin.main

import android.content.Intent
import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.alwin.android.databinding.ActivityMainBinding
import com.alwin.util.SystemUtil

class MainActivity : AppCompatActivity() {

    companion object {
        const val HOME_INDEX = 0
        const val OFFICIAL_INDEX = 1
        const val ME_INDEX = 2
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.pager.adapter = MainFragmentAdapter(this)
        binding.pager.isUserInputEnabled = false
        setClickEvents()
        // 设置沉浸式状态栏后，需要调整toolbar高度
        SystemUtil.setStatusBar(window)
        binding.toolbar.let {
            val params: ViewGroup.LayoutParams = it.layoutParams
            val topMargin = SystemUtil.getStatusBarHeight(this)
            params.height = params.height + topMargin
            it.setPadding(
                it.paddingLeft, it.paddingTop + topMargin,
                it.paddingRight, it.paddingBottom
            )
            it.layoutParams = params
        }

        // startActivity(Intent(this, Main2Activity::class.java))
    }

    private fun setClickEvents() {
        binding.home.setOnClickListener {
            binding.pager.currentItem = HOME_INDEX
        }
        binding.official.setOnClickListener {
            binding.pager.currentItem = OFFICIAL_INDEX
        }
        binding.me.setOnClickListener {
            binding.pager.currentItem = ME_INDEX
        }
    }
}