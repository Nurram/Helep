package com.rex.project.helep.view.fragments.profile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.R
import com.rex.project.helep.databinding.FragmentProfileBinding
import com.rex.project.helep.model.Review
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.editProfile.EditProfileActivity
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

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory(requireActivity().application)
        val viewModel = ViewModelProvider(this, factory)[ProfileViewModel::class.java]
        viewModel.getUserById()?.observe(viewLifecycleOwner) {
            binding.tvName.text = it.name
            binding.tvUsername.text = "@${it.username}"
        }
        val adapter = ProfileAdapter(Review.getDummy())
        binding.rvReviews.adapter = adapter
        binding.rvReviews.layoutManager = LinearLayoutManager(requireContext())

        binding.btnEditProfile.setOnClickListener {
            val i = Intent(requireContext(), EditProfileActivity::class.java)
            startActivity(i)
        }
        binding.btnLogout.setOnClickListener {
            viewModel.logout()

            val i = Intent(requireContext(), LoginActivity::class.java)
            startActivity(i)
            requireActivity().finish()
        }
        binding.ll5Star.setOnClickListener { clickStar(5) }
        binding.ll4Star.setOnClickListener { clickStar(4) }
        binding.ll3Star.setOnClickListener { clickStar(3) }
        binding.ll2Star.setOnClickListener { clickStar(2) }
        binding.ll1Star.setOnClickListener { clickStar(1) }
    }

    private fun clickStar(star: Int) {
        binding.ll5Star.background = null
        binding.ll4Star.background = null
        binding.ll3Star.background = null
        binding.ll2Star.background = null
        binding.ll1Star.background = null

        when (star) {
            5 -> binding.ll5Star.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.border__outline_primary)
            4 -> binding.ll4Star.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.border__outline_primary)
            3 -> binding.ll3Star.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.border__outline_primary)
            2 -> binding.ll2Star.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.border__outline_primary)
            1 -> binding.ll1Star.background =
                AppCompatResources.getDrawable(requireContext(), R.drawable.border__outline_primary)
        }
    }
}