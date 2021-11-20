package com.rex.project.helep.view.fragments.chat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.rex.project.helep.R
import com.rex.project.helep.databinding.FragmentChatBinding
import com.rex.project.helep.model.Chat
import com.rex.project.helep.utils.Constants
import com.rex.project.helep.view.activities.chatRoom.ChatRoomActivity

class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ChatAdapter(Chat.getDummy()) {
            val i = Intent(requireContext(), ChatRoomActivity::class.java)
            i.putExtra(Constants.DATA, it)
            startActivity(i)
        }
        binding.apply {
            rvChat.adapter = adapter
            rvChat.layoutManager = LinearLayoutManager(requireContext())
        }
    }
}