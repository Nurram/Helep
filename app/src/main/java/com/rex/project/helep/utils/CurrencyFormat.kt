package com.rex.project.helep.utils

import java.text.NumberFormat

object CurrencyFormat {
    fun formatRupiah(value: Long): String {
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 0;

        return numberFormat.format(value)
    }

    fun removeFormat(value: String): Long {
        var newValue = value.replace("Rp", "")
        newValue = newValue.replace(".","" )

        return newValue.toLong()
    }
}