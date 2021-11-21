package com.rex.project.helep.view.activities.login

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityLoginBinding
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.home.HomeActivity
import com.rex.project.helep.view.activities.register.RegisterActivity


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )

        val viewModelFactory = ViewModelFactory(application)
        val viewModel = ViewModelProvider(this, viewModelFactory)[LoginViewModel::class.java]
        val isLoggedIn = viewModel.getLoggedIn()
        if (isLoggedIn != -1L) moveToHome()

        val binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()

            viewModel.login(email, password)?.observe(this) { users ->
                if (users.isEmpty()) Toast.makeText(
                    this,
                    R.string.salah_input,
                    Toast.LENGTH_SHORT
                ).show()
                else {
                    viewModel.setLoggedIn(users[0].id)
                    moveToHome()
                }
            }
        }

        binding.tvRegister.setOnClickListener {
            val i = Intent(this, RegisterActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    private fun moveToHome() {
        val i = Intent(this, HomeActivity::class.java)
        startActivity(i)
        finish()
    }
}