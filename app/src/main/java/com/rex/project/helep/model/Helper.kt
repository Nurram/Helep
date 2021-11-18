package com.rex.project.helep.model

import android.os.Parcelable
import com.rex.project.helep.R
import kotlinx.parcelize.Parcelize

@Parcelize
data class Helper(
    val id: Long,
    val avatar: Int,
    val name: String,
    val distance: String,
    val rating: Double,
    val reviewer: Int,
    val price: String
): Parcelable {

    companion object {
        fun getHelpers() = listOf(
        Helper(900, R.drawable.person1, "mich9090", "1 Km", 5.0, 101, "Rp.3.000\n/Task"),
        Helper(901, R.drawable.person2, "Bella_ciao", "1.1 Km", 4.9, 71, "Menyetujui Pembayaran"),
        Helper(902, R.drawable.person3, "Corneliusss", "1.2 Km", 5.0, 126, "Menyetujui Pembayaran"),
        )

        fun getHelperById(id: Long) = getHelpers().filter { it.id == id }[0]
    }
}