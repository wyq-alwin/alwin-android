package com.alwin.main

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.alwin.android.databinding.ActivityMainBinding
import com.alwin.util.SystemUtil
import com.alwin.util.binding

class MainActivity : AppCompatActivity(), Handler.Callback {

    companion object {
        const val HOME_INDEX = 0
        const val OFFICIAL_INDEX = 1
        const val ME_INDEX = 2
        const val MSG = 1
    }

    private val binding: ActivityMainBinding by binding()
    private var time = 0
    private val handler = Handler(Looper.getMainLooper(), this)

    override fun handleMessage(msg: Message): Boolean {
        if (msg.what == MSG) {
            handler.removeMessages(MSG)
            println("@@@@" + time)
            time++
            if (time < 20) {
                // handler.sendEmptyMessage(MSG)
                handler.sendMessage(Message.obtain().apply { what = MSG })
            }
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        binding.pager.currentItem = 1

        Looper.myLooper()?.queue?.addIdleHandler {
            return@addIdleHandler false
        }

        binding.root.post {  }
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

