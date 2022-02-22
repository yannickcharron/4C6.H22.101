package ca.qc.cstj.tipscalculator.core

import java.text.NumberFormat
import java.util.*

object Formatter {

    fun toMoneyFormat(amount: Double): String {
        val numberFormat = NumberFormat.getCurrencyInstance(Locale.CANADA_FRENCH)
        return numberFormat.format(amount)
    }

}