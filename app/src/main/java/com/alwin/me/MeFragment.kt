package com.alwin.me

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alwin.android.databinding.FragmentMeBinding
import com.alwin.login.LoginActivity

class MeFragment : Fragment() {

    private var binding: FragmentMeBinding? = null
    private fun binding() = binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMeBinding.inflate(inflater, container, false)
        return binding().root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding().text.setOnClickListener {
            context?.startActivity(Intent(context, LoginActivity::class.java))
        }
    }
}