package com.alwin.me

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.alwin.android.R
import com.alwin.android.databinding.FragmentMeBinding
import com.alwin.login.LoginActivity
import com.alwin.util.AccountUtils
import com.alwin.util.binding
import kotlinx.coroutines.launch

class MeFragment : Fragment(R.layout.fragment_me) {

    private val binding: FragmentMeBinding by binding()

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            binding.text.let {
                if (AccountUtils.isLogin()) {
                    it.text = "用户名: ${AccountUtils.getUserName()}"
                } else {
                    it.text = "去登陆"
                    it.setOnClickListener {
                        context?.startActivity(Intent(context, LoginActivity::class.java))
                    }
                }
            }
        }
    }
}