package com.alwin.login

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.alwin.android.databinding.ActivityLoginBinding

class LoginActivity : FragmentActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        binding.apply {

            close.setOnClickListener {
                finish()
            }
        }
    }
}