package com.alwin.login

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.FragmentActivity
import com.alwin.android.databinding.ActivityLoginBinding
import com.alwin.util.binding
import com.alwin.webview.WebActivity

class LoginActivity : FragmentActivity() {
    private val binding: ActivityLoginBinding by binding()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onResume() {
        super.onResume()
        binding.apply {
            close.setOnClickListener {
                finish()
            }
            login.setOnClickListener {
                // lifecycleScope.launch {
                //     val result =
                //         loginViewModel.toLogin(username.text.toString(), password.text.toString())
                //     if (result) {
                //         finish()
                //     }
                // }

                startActivity(Intent(this@LoginActivity, WebActivity::class.java))
            }
            register.setOnClickListener {
                startActivity(Intent(this@LoginActivity, WebActivity::class.java))
                // lifecycleScope.launch {
                //     val result = loginViewModel.toRegister(
                //         username.text.toString(),
                //         password.text.toString()
                //     )
                //     if (result) {
                //         finish()
                //     }
                // }
            }
            username.addTextChangedListener {
                updateButton()
            }
            password.addTextChangedListener {
                updateButton()
            }
        }
    }

    private fun updateButton() {
        binding.run {
            login.isEnabled = true
            register.isEnabled = true
            // if (username.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
            //     login.isEnabled = false
            //     register.isEnabled = false
            // } else {
            //     login.isEnabled = true
            //     register.isEnabled = true
            // }
        }
    }
}