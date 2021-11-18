package com.rex.project.helep.view.fragments.find

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.R
import com.rex.project.helep.databinding.FragmentFindBinding
import com.rex.project.helep.local.entities.TaskAndUser
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.detailTask.DetailTaskActivity

class FindFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentFindBinding
    private lateinit var viewModel: FindViewModel
    private lateinit var adapter: FindAdapter

    private var categories = listOf<TaskAndUser>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(requireActivity(), factory)[FindViewModel::class.java]

        adapter = FindAdapter {
            val i = Intent(requireContext(), DetailTaskActivity::class.java)
            i.putExtra("data", it)
            startActivity(i)
        }

        viewModel.getAllTasks()?.observe(viewLifecycleOwner) {
            adapter.setData(it)
            categories = it
        }

        binding.apply {
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.categories,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spCategories.adapter = adapter
            }
            spCategories.onItemSelectedListener = this@FindFragment
            rvTasks.adapter = adapter
            rvTasks.layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        val selectedItem = p0?.getItemAtPosition(p2)
        val filteredList = categories.filter { it.task.category == selectedItem }
        adapter.setData(filteredList)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}