package com.rex.project.helep.view.fragments.posts

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.databinding.ItemProgressListBinding
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.utils.CurrencyFormat

class ProgressAdapter(
    private val onItemClick: (task: Task) -> Unit
) : RecyclerView.Adapter<ProgressAdapter.PogressHolder>() {
    private val tasks = arrayListOf<Task>()

    inner class PogressHolder(
        private val binding: ItemProgressListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(task: Task) {
            binding.apply {
                tvPostName.text = task.category
                tvPostDesc.text = task.shortDesc
                tvPostPrice.text = CurrencyFormat.formatRupiah(task.price)
                tvTaskProgress.text = "0%\nProgress"

                btnTaskViewProgress.setOnClickListener {
                    onItemClick(task)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PogressHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProgressListBinding.inflate(inflater, parent, false)

        return PogressHolder(binding)
    }

    override fun onBindViewHolder(holder: PogressHolder, position: Int) =
        holder.bind(tasks[position])

    override fun getItemCount(): Int = tasks.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }
}