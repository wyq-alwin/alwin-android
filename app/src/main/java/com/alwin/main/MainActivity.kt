package com.alwin.main

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.alwin.android.databinding.ActivityMainBinding
import com.alwin.util.SystemUtil

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, Fragment())
            .commitAllowingStateLoss()

        // 设置沉浸式状态栏后，需要调整toolbar高度
        SystemUtil.setStatusBar(window)
        binding.toolbar.let {
            val params: ViewGroup.LayoutParams = it.layoutParams
            val topMargin: Int = SystemUtil.getStatusBarHeight(window, this)
            params.height = params.height + topMargin
            it.setPadding(
                it.paddingLeft, it.paddingTop + topMargin,
                it.paddingRight, it.paddingBottom
            )
            it.layoutParams = params
        }
    }
}