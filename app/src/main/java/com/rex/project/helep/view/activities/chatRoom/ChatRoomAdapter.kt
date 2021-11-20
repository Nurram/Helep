package com.rex.project.helep.view.activities.chatRoom

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rex.project.helep.databinding.ChatRoomItemListBinding

class ChatRoomAdapter(): RecyclerView.Adapter<ChatRoomAdapter.ChatRoomHolder>() {
    private val chats = arrayListOf<Map<String, Any>>()

    inner class ChatRoomHolder(
        private val binding: ChatRoomItemListBinding
    ): RecyclerView.ViewHolder(binding.root) {
        fun bind(chat: Map<String, Any>) {
            binding.apply {
                val chatString = chat["chat"] as String
                val chatTime = chat["time"] as String
                val isSender = chat["sender"] as Boolean

                if (isSender) {
                    llReceiver.visibility = View.GONE
                    senderChat.text = chatString
                    senderTime.text = chatTime
                } else {
                    llSender.visibility = View.GONE
                    receieverChat.text = chatString
                    receiverTime.text = chatTime
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatRoomHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ChatRoomItemListBinding.inflate(inflater, parent, false)

        return ChatRoomHolder(binding)
    }

    override fun onBindViewHolder(holder: ChatRoomHolder, position: Int) =
        holder.bind(chats[position])

    override fun getItemCount(): Int = chats.size

    fun setItem(chats: List<Map<String, Any>>) {
        this.chats.clear()
        this.chats.addAll(chats)
        notifyDataSetChanged()
    }
    fun addChat(chat: Map<String, Any>) {
        chats.add(chat)
        notifyDataSetChanged()
    }
}