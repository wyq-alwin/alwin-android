package com.alwin.main

import android.content.Context
import android.location.LocationManager
import android.os.Bundle
import android.telephony.TelephonyManager
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
        binding.pager.currentItem = 1
        // startActivity(Intent(this, Main2Activity::class.java))

        // val telephonyManager =  getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        // val con = Class.forName("android.telephony.TelephonyManager")
        //     .getConstructor(Context::class.java, Int::class.java)
        // val tel = con.newInstance(this, -1) as TelephonyManager
        // tel.allCellInfo
        // tel.allCellInfo
        // LocationManager.KEY_LOCATIONS
        // println(tel.networkCountryIso)
        // drawBadge()
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