package com.rex.project.helep.view.fragments.find

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.databinding.PeoplePostItemListBinding
import com.rex.project.helep.local.entities.TaskAndUser
import com.rex.project.helep.utils.CurrencyFormat

class FindAdapter(
    private val onItemClick: (task: TaskAndUser) -> Unit
) : RecyclerView.Adapter<FindAdapter.FindHolder>() {
    private val taskAndUserList = arrayListOf<TaskAndUser>()

    inner class FindHolder(
        private val binding: PeoplePostItemListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(task: TaskAndUser) {
            val imageByte = task.user.image

            binding.apply {
                if (imageByte.isNotEmpty()) {
                    val bitmap = BitmapFactory.decodeByteArray(imageByte, 0, imageByte.size)
                    civAvatar.setImageBitmap(bitmap)
                }

                tvName.text = task.user.username
                tvDesc.text = task.task.shortDesc
                tvPrice.text = CurrencyFormat.formatRupiah(task.task.price)

                btnDetail.setOnClickListener { onItemClick(task) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FindHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PeoplePostItemListBinding.inflate(inflater, parent, false)

        return FindHolder(binding)
    }

    override fun onBindViewHolder(holder: FindHolder, position: Int) =
        holder.bind(taskAndUserList[position])

    override fun getItemCount(): Int = taskAndUserList.size

    fun setData(tasks: List<TaskAndUser>) {
        this.taskAndUserList.clear()
        this.taskAndUserList.addAll(tasks)
        notifyDataSetChanged()
    }
}