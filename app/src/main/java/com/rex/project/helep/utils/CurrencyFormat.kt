package com.rex.project.helep.utils

import java.text.NumberFormat

object CurrencyFormat {
    fun formatRupiah(value: Long): String {
        val numberFormat = NumberFormat.getCurrencyInstance()
        numberFormat.maximumFractionDigits = 0;

        return numberFormat.format(value)
    }
}