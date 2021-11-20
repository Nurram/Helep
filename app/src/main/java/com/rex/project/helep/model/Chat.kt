package com.rex.project.helep.model

import android.os.Parcelable
import com.rex.project.helep.R
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class Chat(
    val id: Long,
    val avatar: Int,
    val username: String,
    val chats: @RawValue List<Map<String, Any>>,
    val status: String,
): Parcelable {

    companion object {
        fun getDummy() = arrayListOf(
            Chat(
                0,
                R.drawable.person7,
                "Jessica",
                listOf(
                    mapOf(
                        "chat" to "Terima kasih atas bantuannya.",
                        "time" to "20:47 PM",
                        "sender" to false
                    )
                ),
                "Offline"
            ),
            Chat(
                1,
                R.drawable.person8,
                "Alvin",
                listOf(
                    mapOf(
                        "chat" to "Pakai jasa saya terus ya.",
                        "time" to "Yesterday",
                        "sender" to true
                    )
                ),
                "Idle"
            ),
            Chat(
                2,
                R.drawable.person9,
                "Cindy",
                listOf(
                    mapOf(
                        "chat" to "Terima kasih atas donasinya.",
                        "time" to "31/10/2021",
                        "sender" to true
                    )
                ),
                "Online"
            ),
        )
    }
}