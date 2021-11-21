package com.rex.project.helep.view.activities.editProfile

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ActivityEditProfileBinding
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.home.HomeActivity

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = ViewModelFactory(application)
        val viewModel = ViewModelProvider(this, factory)[EditProfileViewModel::class.java]

        val binding = ActivityEditProfileBinding.inflate(layoutInflater)
        binding.apply {
            setContentView(root)

            viewModel.getUserById()?.observe(this@EditProfileActivity) {
                etName.setText(it.name)
                etUsername.setText(it.username)
            }

            ivBack.setOnClickListener { finish() }
            btnKonfirmasi.setOnClickListener {
                val name = etName.text.toString()
                val username = etUsername.text.toString()

                viewModel.updateUserNameAndName(username, name)
            }
        }

        viewModel.getResult().observe(this@EditProfileActivity) {
            if (it != -1) {
                Toast.makeText(
                    this@EditProfileActivity,
                    R.string.berhasil,
                    Toast.LENGTH_SHORT
                ).show()

                val i = Intent(this@EditProfileActivity, HomeActivity::class.java)
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                startActivity(i)
            }
        }
    }
}