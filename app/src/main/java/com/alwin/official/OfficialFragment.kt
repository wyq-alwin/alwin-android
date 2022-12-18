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


        val displayMetrics = resources.displayMetrics
        println("@@@@@@" + displayMetrics)

        binding.root.post {  }


    }
}