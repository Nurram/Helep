package com.rex.project.helep.view.activities.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityRegisterBinding
import com.rex.project.helep.local.entities.User
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.login.LoginActivity

class RegisterActivity : AppCompatActivity() {
    private lateinit var viewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
            WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
        )

        val binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewmodelFactory = ViewModelFactory(application)
        viewModel = ViewModelProvider(this, viewmodelFactory)[RegisterViewModel::class.java]
        viewModel.getRegisteredId().observe(this) {
            if (it != -1L) {
                viewModel.setLoggedIn(it)
                viewModel.addWallet(it)
                Toast.makeText(this, R.string.berhasil, Toast.LENGTH_SHORT).show()
                moveToLogin()
            }
        }

        binding.apply {
            btnRegister.setOnClickListener {
                val username = etUsername.text.toString()
                val email = etEmail.text.toString()
                val phoneNumber = etPhoneNumber.text.toString()
                val password = etPassword.text.toString()
                val passwordConf = etPasswordConfirm.text.toString()

                val user = User(0, username, email, phoneNumber, password, passwordConf, byteArrayOf())
                register(user)
            }

            tvLogin.setOnClickListener { moveToLogin() }
        }
    }

    private fun register(user: User) {
        if (
            user.username.isEmpty()
            || user.email.isEmpty()
            || user.phoneNumber.isEmpty()
            || user.password.isEmpty()
            || user.passwordConfirm.isEmpty()) {
            Toast.makeText(this, R.string.isi_semua_form, Toast.LENGTH_SHORT).show()
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(user.email).matches()){
            Toast.makeText(this, R.string.masukan_email_valid, Toast.LENGTH_SHORT).show()
        } else if(user.password.length < 6) {
            Toast.makeText(this, R.string.password_pendek, Toast.LENGTH_SHORT).show()
        } else if(user.password != user.passwordConfirm) {
            Toast.makeText(this, R.string.password_tidak_sama, Toast.LENGTH_SHORT).show()
        } else {
            viewModel.register(user)
        }
    }

    private fun moveToLogin() {
        val i = Intent(this, LoginActivity::class.java)
        startActivity(i)
        finish()
    }
}