package com.rex.project.helep.model

import android.os.Parcelable
import com.rex.project.helep.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class HelperTask(
    val id: Long,
    val avatar: Int,
    val username: String,
    val taskDesc: String,
    val category: String,
    val distance: Double,
    val price: Long,
    val taskOne: String,
    val taskOneCount: Int,
    val taskTwo: String,
    val taskTwoCount: Int
): Parcelable {
    companion object {
        fun getDummyTask() = listOf(
            HelperTask(
                9003,
                R.drawable.person4,
                "Brando",
                "Mainkan akun Genshin Impact saya dan selesaikan Daily Commission saya dan Farm Artifact di Domain Elektro.",
                "Permainan",
                1.0,
                30000,
                "Selesaikan Daily Comission",
                1,
                "Habiskan Resin Di Domain Elektro",
                1
                ),
            HelperTask(
                9004,
                R.drawable.person5,
                "Alex6789",
                "Tutorin saya mengenai pelajaran Sejarah dan berikan arahan bagaimana saya harus mengerjakan tugas ini.",
                "Edukasi",
                1.1,
                200000,
                "Selesaikan Daily Comission",
                1,
                "Habiskan Resin Di Domain Elektro",
                1
            ),
            HelperTask(
                9005,
                R.drawable.person6,
                "ank_singh",
                "Tolong pesankan Pizza di PizzaHut dan antarkan ke rumah saya. Harga bisa diatur.",
                "Makanan",
                1.2,
                30000,
                "Selesaikan Daily Comission",
                1,
                "Habiskan Resin Di Domain Elektro",
                1
            ),
        )
    }
}