package com.rex.project.helep.view.fragments.posts

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.databinding.PostItemListBinding
import com.rex.project.helep.local.entities.Task
import com.rex.project.helep.utils.CurrencyFormat

class PostAdapter(
    private val onItemClick: (task: Task) -> Unit
): RecyclerView.Adapter<PostAdapter.PostHolder>() {
    private val tasks = arrayListOf<Task>()

    inner class PostHolder(
        private val binding: PostItemListBinding
        ): RecyclerView.ViewHolder(binding.root) {
            fun bind(task: Task) {
                binding.apply {

                    tvPostName.text = task.category
                    tvPostDesc.text = task.shortDesc
                    tvPostPrice.text = CurrencyFormat.formatRupiah(task.price)
                    tvTaskReceive.text = "3\nMenerima\nTask"

                    btnTaskChooseHelper.setOnClickListener {
                        onItemClick(task)
                    }
                }
            }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
       val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemListBinding.inflate(inflater, parent, false)

        return PostHolder(binding)
    }

    override fun onBindViewHolder(holder: PostHolder, position: Int) = holder.bind(tasks[position])

    override fun getItemCount(): Int = tasks.size

    fun setList(tasks: List<Task>) {
        this.tasks.clear()
        this.tasks.addAll(tasks)
        notifyDataSetChanged()
    }
}