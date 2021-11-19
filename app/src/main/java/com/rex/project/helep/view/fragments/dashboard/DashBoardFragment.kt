package com.rex.project.helep.view.fragments.dashboard

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.R
import com.rex.project.helep.databinding.FragmentDashBoardBinding
import com.rex.project.helep.model.HelperTask
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.utils.CurrencyFormat
import com.rex.project.helep.view.ViewModelFactory
import com.rex.project.helep.view.activities.detailTask.DetailTaskActivity
import com.rex.project.helep.view.activities.topUp.TopUpActivity

class DashBoardFragment : Fragment() {
    private lateinit var binding: FragmentDashBoardBinding
    private lateinit var viewModel: DashboardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDashBoardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val factory = ViewModelFactory(requireActivity().application)
        viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]
        viewModel.getWalletByUserId()?.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvBalance.text = CurrencyFormat.formatRupiah(it.balance)
            } else {
                binding.tvBalance.text = CurrencyFormat.formatRupiah(0)
            }
        }

        val helperTaskAdapter = HelperTaskAdapter {
            val i = Intent(requireContext(), DetailTaskActivity::class.java)
            i.putExtra(Constants.DATA, it)
            startActivity(i)
        }

        helperTaskAdapter.setData(HelperTask.getDummyTask())
        binding.rvTasks.apply {
            adapter = helperTaskAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        binding.btnTopup.setOnClickListener {
            val i = Intent(requireContext(), TopUpActivity::class.java)
            startActivity(i)
        }
    }
}