package com.rex.project.helep.view.fragments.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.databinding.ItemPeoplePostListBinding
import com.rex.project.helep.model.HelperTask
import com.rex.project.helep.utils.CurrencyFormat

class HelperTaskAdapter(
    private val onItemClick: (task: HelperTask) -> Unit
) : RecyclerView.Adapter<HelperTaskAdapter.HelperTaskHolder>() {
    private val helperTaskList = arrayListOf<HelperTask>()

    inner class HelperTaskHolder(
        private val binding: ItemPeoplePostListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(task: HelperTask) {
            binding.apply {
                civAvatar.setImageResource(task.avatar)
                tvName.text = task.username
                tvDesc.text = task.taskDesc
                tvPrice.text = "${CurrencyFormat.formatRupiah(task.price)}\n/Task"
                tvDistance.text = "${task.distance} Km"
                btnDetail.setOnClickListener { onItemClick(task) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelperTaskHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemPeoplePostListBinding.inflate(inflater, parent, false)

        return HelperTaskHolder(binding)
    }

    override fun onBindViewHolder(holder: HelperTaskHolder, position: Int) =
        holder.bind(helperTaskList[position])

    override fun getItemCount(): Int = helperTaskList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setData(tasks: List<HelperTask>) {
        this.helperTaskList.clear()
        this.helperTaskList.addAll(tasks)
        notifyDataSetChanged()
    }
}