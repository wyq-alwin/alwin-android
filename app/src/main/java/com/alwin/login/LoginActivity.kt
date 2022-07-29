package com.alwin.login

import android.content.Context
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.lifecycleScope
import com.alwin.android.databinding.ActivityLoginBinding
import com.alwin.login.viewmodel.LoginViewModel
import com.alwin.util.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LoginActivity : FragmentActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels()

    private val USER_NAME = stringPreferencesKey("username")
    val usernameFlow: Flow<String> = this.dataStore.data.map { preferences ->
        // No type safety.
        preferences[USER_NAME] ?: ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.apply {
            close.setOnClickListener {
                finish()
            }
            login.setOnClickListener {
                lifecycleScope.launch {
                    loginViewModel.toLogin(username.text.toString(), password.text.toString())

                }
            }
            register.setOnClickListener {
                lifecycleScope.launch {
                    loginViewModel.toRegister(username.text.toString(), password.text.toString())
                }
            }
            username.addTextChangedListener {
                updateButton()
            }
            password.addTextChangedListener {
                updateButton()
            }
        }
    }

    private fun ActivityLoginBinding.updateButton() {
        if (username.text.isNullOrEmpty() || password.text.isNullOrEmpty()) {
            login.isEnabled = false
            register.isEnabled = false
        } else {
            login.isEnabled = true
            register.isEnabled = true
        }
    }

    suspend fun saveUsername( username: String) {
        dataStore.edit { settings ->
            settings[USER_NAME] = username
        }
    }
}