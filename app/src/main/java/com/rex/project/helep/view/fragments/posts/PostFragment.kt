package com.rex.project.helep.view.fragments.posts

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.databinding.FragmentPostBinding
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.addTask.AddTask
import com.rex.project.helep.view.activities.helper.HelperActivity
import com.rex.project.helep.view.activities.viewProgress.ViewProgressActivity

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

        val factory = ViewModelFactory(requireActivity().application)
        val viewModel = ViewModelProvider(this, factory)[PostViewModel::class.java]

        val pendingAdapter = PostAdapter {
            val i = Intent(requireContext(), HelperActivity::class.java)
            i.putExtra(Constants.TASK_ID, it.id)
            startActivity(i)
        }

        val onProgressAdapter = ProgressAdapter {
            val i = Intent(requireContext(), ViewProgressActivity::class.java)
            i.putExtra(Constants.DATA, it.price)
            i.putExtra(Constants.TASK_ID, it.id)
            startActivity(i)
        }

        viewModel.getPendingTaskByUserId()?.observe(viewLifecycleOwner) {
            pendingAdapter.setList(it)
        }

        viewModel.getOnProgressTaskByUserId()?.observe(viewLifecycleOwner) {
            if (it.isNotEmpty()) {
                binding.tvProgress.visibility = View.VISIBLE
                onProgressAdapter.setList(it)
            }
        }

        binding.apply {
            rvProgress.adapter = onProgressAdapter
            rvProgress.layoutManager = object: LinearLayoutManager(requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }

            rvPosts.adapter = pendingAdapter
            rvPosts.layoutManager = object: LinearLayoutManager(requireContext()) {
                override fun canScrollVertically(): Boolean {
                    return false
                }
            }

            fabAddPost.setOnClickListener {
                val i = Intent(requireContext(), AddTask::class.java)
                startActivity(i)
            }
        }
    }
}