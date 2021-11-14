package com.rex.project.helep.view.fragments.posts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rex.project.helep.R
import com.rex.project.helep.databinding.FragmentPostBinding
import com.rex.project.helep.view.activities.addTask.AddTask

class PostFragment : Fragment() {
    private lateinit var binding: FragmentPostBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.fabAddPost.setOnClickListener {
            val i = Intent(requireContext(), AddTask::class.java)
            startActivity(i)
        }
    }
}