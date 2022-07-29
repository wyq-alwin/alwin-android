package com.alwin.official

import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alwin.android.databinding.FragmentOfficialBinding
import com.alwin.util.dp2px

class OfficialFragment : Fragment() {

    private var binding: FragmentOfficialBinding? = null
    private fun binding() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOfficialBinding.inflate(inflater, container, false)

        val paint = Paint().apply {
            textSize = 14.dp2px.toFloat()
        }
        var text = "wfwe"
        val measuredWidth = floatArrayOf()
        binding().text1.setOnClickListener {
            binding().text1.text = text
            val count = paint.breakText(
                text, 0, text.length, true,
                100.dp2px.toFloat(), measuredWidth
            )
            Log.d("wyq", count.toString())
            Log.d("wyq", measuredWidth.contentToString())
            Log.d("wyq", paint.measureText(text).toString())
        }
        binding().text2.setOnClickListener {
            text = "sadfasfasfassaf"
            val count = paint.breakText(
                text, 0, text.length, true,
                100.dp2px.toFloat(), measuredWidth
            )
            Log.d("wyq", count.toString())
            Log.d("wyq", measuredWidth.contentToString())
            binding().text2.text = text
            Log.d("wyq", paint.measureText(text).toString())
        }
        return binding().root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}