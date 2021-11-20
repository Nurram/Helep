package com.rex.project.helep.view.activities.chatRoom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.databinding.ActivityChatRoomBinding
import com.rex.project.helep.model.Chat
import com.rex.project.helep.utils.Constants
import java.text.SimpleDateFormat
import java.util.*

class ChatRoomActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatRoomBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val chat = intent.getParcelableExtra<Chat>(Constants.DATA)
        val adapter = ChatRoomAdapter()
        chat?.let {
            binding.tvName.text = it.username
            adapter.setItem(it.chats)
        }

        binding.apply {
            ivBack.setOnClickListener { finish() }
            rvChat.adapter = adapter
            rvChat.layoutManager = LinearLayoutManager(this@ChatRoomActivity)
            ivSend.setOnClickListener {
                val inputtedChat = etChat.text.toString()
                etChat.text.clear()

                val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
                val currentDate = sdf.format(Date())

                adapter.addChat(
                    mapOf(
                        "chat" to inputtedChat,
                        "time" to currentDate,
                        "sender" to true
                    )
                )
            }
        }
    }
}