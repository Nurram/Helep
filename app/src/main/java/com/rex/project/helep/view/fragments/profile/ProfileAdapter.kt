package com.rex.project.helep.view.fragments.profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.databinding.ItemReviewListBinding
import com.rex.project.helep.model.Review

class ProfileAdapter(
    private val reviews: List<Review>
) : RecyclerView.Adapter<ProfileAdapter.ProfileHolder>() {
    inner class ProfileHolder(
        private val binding: ItemReviewListBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(review: Review) {
            binding.apply {
                tvName.text = review.username
                tvDate.text = review.date
                textView3.text = review.review
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReviewListBinding.inflate(inflater, parent, false)

        return ProfileHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileHolder, position: Int) =
        holder.bind(reviews[position])

    override fun getItemCount(): Int = reviews.size
}