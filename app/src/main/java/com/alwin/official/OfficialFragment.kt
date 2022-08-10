package com.alwin.official

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.alwin.android.databinding.FragmentOfficialBinding

class OfficialFragment : Fragment() {

    private var binding: FragmentOfficialBinding? = null
    private fun binding() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOfficialBinding.inflate(inflater, container, false)

        binding().apply {

        }

        return binding().root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}