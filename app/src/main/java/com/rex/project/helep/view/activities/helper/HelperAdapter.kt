package com.rex.project.helep.view.activities.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.databinding.HelperItemListBinding
import com.rex.project.helep.model.Helper

class HelperAdapter(
    private val helpers: List<Helper>,
    private val onClick: (helper: Helper) -> Unit
): RecyclerView.Adapter<HelperAdapter.HelperViewHolder>() {

    inner class HelperViewHolder(
        private val binding: HelperItemListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(helper: Helper) {
            binding.apply {
                civAvatar.setImageResource(helper.avatar)
                tvName.text = helper.name
                tvDistance.text = helper.distance
                tvRating.text = helper.rating.toString()
                tvReview.text = "(${helper.reviewer})"
                tvPrice.text = helper.price

                btnChoose.setOnClickListener {
                    onClick(helper)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelperViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = HelperItemListBinding.inflate(inflater, parent, false)

        return HelperViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HelperViewHolder, position: Int) =
        holder.bind(helpers[position])

    override fun getItemCount(): Int = helpers.size
}