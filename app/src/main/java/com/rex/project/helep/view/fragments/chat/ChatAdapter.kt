package com.rex.project.helep.view.fragments.chat

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.R
import com.rex.project.helep.databinding.ItemChatListBinding
import com.rex.project.helep.model.Chat

class ChatAdapter(
    private val chats: ArrayList<Chat>,
    private val onClick: (chat: Chat) -> Unit
) : RecyclerView.Adapter<ChatAdapter.ChatHolder>() {

    inner class ChatHolder(
        private val binding: ItemChatListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Chat) {
            binding.apply {
                civAvatar.setImageResource(chat.avatar)
                tvName.text = chat.username
                tvChatCount.text = chat.chats.size.toString()

                val firstChat = chat.chats[0]
                tvChat.text = firstChat["chat"] as String
                tvTime.text = firstChat["time"] as String

                when (chat.status) {
                    "Offline" -> {
                        ivStatus.setImageResource(R.drawable.circle_red)
                    }
                    "Online" -> {
                        ivStatus.setImageResource(R.drawable.circle_green)
                    }
                    else -> {
                        ivStatus.setImageResource(R.drawable.circle_green_white)
                    }
                }

                itemView.setOnClickListener { onClick(chat) }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemChatListBinding.inflate(inflater, parent, false)

        return ChatHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatHolder, position: Int) =
        holder.bind(chats[position])

    override fun getItemCount(): Int = chats.size
}