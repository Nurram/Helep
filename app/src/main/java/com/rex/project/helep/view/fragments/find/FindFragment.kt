package com.rex.project.helep.view.fragments.find

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.R
import com.rex.project.helep.databinding.FragmentFindBinding
import com.rex.project.helep.model.HelperTask
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.view.activities.detailTask.DetailTaskActivity
import com.rex.project.helep.view.fragments.dashboard.HelperTaskAdapter

class FindFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FragmentFindBinding
    private lateinit var adapter: HelperTaskAdapter

    private val dummyTasks = HelperTask.getDummyTask()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFindBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HelperTaskAdapter {
            val i = Intent(requireContext(), DetailTaskActivity::class.java)
            i.putExtra(Constants.DATA, it)
            startActivity(i)
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
        val filteredList = dummyTasks.filter { it.category == selectedItem }
        adapter.setData(filteredList.sortedBy { it.distance })

        binding.tvExpensive.setOnClickListener {
            binding.tvExpensive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            binding.tvNearest.setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
            adapter.setData(filteredList.sortedBy { it.price })
        }
        binding.tvNearest.setOnClickListener {
            binding.tvExpensive.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.black
                )
            )
            binding.tvNearest.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.colorPrimary
                )
            )
            adapter.setData(filteredList.sortedBy { it.distance })
        }
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}
}