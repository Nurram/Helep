package com.rex.project.helep.view.fragments.profile

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.rex.project.helep.R
import com.rex.project.helep.databinding.FragmentProfileBinding
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.login.LoginActivity

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory(requireActivity().application)
        val viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]

        binding.tvLogout.setOnClickListener {
            viewModel.logout()

            val i = Intent(requireContext(), LoginActivity::class.java)
            startActivity(i)
            requireActivity().finish()
        }
    }
}