package com.alwin.official

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.alwin.android.R
import com.alwin.android.databinding.FragmentOfficialBinding
import com.alwin.util.binding

class OfficialFragment : Fragment(R.layout.fragment_official) {
    companion object {
        const val TAG = "OfficialFragment"
    }

    private val binding: FragmentOfficialBinding by binding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // val options = RequestOptions()
        //     .placeholder(R.drawable.ic_launcher_background)
        //     .error(R.mipmap.ic_launcher)
        //     .diskCacheStrategy(DiskCacheStrategy.NONE)
        //     .override(200, 100)
        //
        // Glide.with(this)
        //     .load("https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/7/15/17351bf3e5e2823c~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.image")
        //     .apply(options)
        //     .into(binding.img)::class.java
        val st =
            "https://p1-jj.byteimg.com/tos-cn-i-t2oaga2asx/gold-user-assets/2020/7/15/17351bf3e5e2823c~tplv-t2oaga2asx-zoom-in-crop-mark:4536:0:0:0.image"
        binding.img.setImageURI(st)


        println(System.getProperty("os.name") + " @@@@@")
    }
}