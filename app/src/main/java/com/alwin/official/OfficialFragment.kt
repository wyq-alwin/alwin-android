package com.alwin.official

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alwin.android.R
import com.alwin.android.databinding.FragmentOfficialBinding
import com.alwin.util.binding
import com.shuyu.gsyvideoplayer.GSYVideoManager
import dalvik.system.DexClassLoader
import okio.buffer
import okio.sink
import okio.source
import java.io.File

class OfficialFragment : Fragment(R.layout.fragment_official) {
    companion object {
        const val TAG = "OfficialFragment"
    }

    private val binding: FragmentOfficialBinding by binding()
    // private lateinit var orientationUtils: OrientationUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        // binding.videoItemPlayer.apply {
        //     val source1 = "http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4"
        //     setUp(source1, true, "测试视频")
        //     //增加封面
        //     val imageView = ImageView(context)
        //     imageView.scaleType = ImageView.ScaleType.CENTER
        //     imageView.setImageResource(R.drawable.avatar)
        //     thumbImageView = imageView
        //     //增加title
        //     titleTextView.visibility = View.VISIBLE
        //     //设置返回键
        //     backButton.visibility = View.VISIBLE
        //     //设置旋转
        //     orientationUtils = OrientationUtils(activity, this)
        //     //设置全屏按键功能,这是使用的是选择屏幕，而不是全屏
        //     fullscreenButton.setOnClickListener {
        //         // 不需要屏幕旋转，还需要设置 setNeedOrientationUtils(false)
        //         orientationUtils.resolveByClick();
        //         onDestroy()
        //     }
        //     //是否可以滑动调整
        //     setIsTouchWiget(true)
        //     //设置返回按键功能
        //     backButton.setOnClickListener { activity?.onBackPressed() }
        //
        //     ///不需要屏幕旋转
        //     //videoPlayer.setNeedOrientationUtils(false);
        //     startPlayLogic()
        // }
        binding.videoItemPlayer.visibility = View.GONE
    }

    private fun testLoadPlugin() {
        val apk = File(requireContext().cacheDir.toString() + "/loadplugin.apk")
        try {
            requireContext().assets.open("loadplugin.apk").source().use {
                apk.sink().buffer().use { sink ->
                    sink.writeAll(it)
                }
            }
        } catch (e: Exception) {
            println(e)
        }
        val classLoader = DexClassLoader(apk.path, requireContext().cacheDir.path, null, null)
        try {
            val pluginClass = classLoader.loadClass("com.alwin.loadplugin.LoaderSample")
            val constructor = pluginClass.declaredConstructors[0]
            constructor.isAccessible = true
            val instance = constructor.newInstance()
            val pluginMethod = pluginClass.getDeclaredMethod("shout")
            pluginMethod.isAccessible = true
            pluginMethod.invoke(instance)
        } catch (e: Exception) {
            println(e)
        }
    }

    override fun onPause() {
        super.onPause()
        binding.videoItemPlayer.onVideoPause()
    }

    override fun onResume() {
        super.onResume()
        binding.videoItemPlayer.onVideoResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        GSYVideoManager.releaseAllVideos()
        // orientationUtils.releaseListener()
        //释放所有
        binding.videoItemPlayer.setVideoAllCallBack(null)
    }
}