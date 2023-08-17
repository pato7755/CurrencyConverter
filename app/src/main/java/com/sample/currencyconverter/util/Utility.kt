package com.sample.currencyconverter.util

import com.sample.currencyconverter.domain.model.rates.Rates
import com.sample.currencyconverter.domain.model.rates.RatesAndCodes

object Utility {
    private fun getFlagEmoji(countryCode: String): String {
        val firstLetter = Character.codePointAt(countryCode, 0) - 0x41 + 0x1F1E6
        val secondLetter = Character.codePointAt(countryCode, 1) - 0x41 + 0x1F1E6
        return String(Character.toChars(firstLetter)) + String(Character.toChars(secondLetter))
    }

    fun getFlagEmojiFromCurrencyCode(currencyCode: String): String {
        val countryCode = currencyCode.substring(0, 2)
        return getFlagEmoji(countryCode)
    }

    fun getCurrencySymbol(currencyCode: String): String {
        return when (currencyCode) {
            "CAD" -> "$"
            "CHF" -> "CHF"
            "EUR" -> "€"
            "GBP" -> "£"
            "GHS" -> "₵"
            "JPY" -> "¥"
            "USD" -> "$"
            "ZAR" -> "R"
            "AUD" -> "$"
            else -> ""
        }
    }

    fun getCurrencyName(currencyCode: String): String {
        return when (currencyCode) {
            "CAD" -> "Canadian Dollar"
            "CHF" -> "Swiss Franc"
            "EUR" -> "Euro"
            "GBP" -> "British Pound"
            "GHS" -> "Ghanaian Cedi"
            "JPY" -> "Japanese Yen"
            "USD" -> "United States Dollar"
            "ZAR" -> "South African Rand"
            "AUD" -> "Australian Dollar"
            else -> ""
        }
    }

    fun getCurrencyCodeList(rates: Rates): List<RatesAndCodes> {
        return listOf(
            RatesAndCodes("CAD", rates.cAD),
            RatesAndCodes("CHF", rates.cHF),
            RatesAndCodes("EUR", rates.eUR),
            RatesAndCodes("GBP", rates.gBP),
            RatesAndCodes("GHS", rates.gHS),
            RatesAndCodes("JPY", rates.jPY),
            RatesAndCodes("USD", rates.uSD),
            RatesAndCodes("ZAR", rates.zAR),
            RatesAndCodes("AUD", rates.aUD)
        ).sortedBy { it.code }
    }

}