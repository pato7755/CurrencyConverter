package com.sample.currencyconverter.data.remote.dto.rates


import com.google.gson.annotations.SerializedName
import com.sample.currencyconverter.domain.model.rates.Rates

data class ConversionRates(
    @SerializedName("CAD")
    val cAD: Double,
    @SerializedName("CHF")
    val cHF: Double,
    @SerializedName("EUR")
    val eUR: Double,
    @SerializedName("GBP")
    val gBP: Double,
    @SerializedName("GHS")
    val gHS: Double,
    @SerializedName("JPY")
    val jPY: Double,
    @SerializedName("USD")
    val uSD: Double,
    @SerializedName("ZAR")
    val zAR: Double,
    @SerializedName("AUD")
    val aUD: Double
) {
    fun dtoToDomainMapper(): Rates {
        return Rates(
            cAD = cAD,
            cHF = cHF,
            eUR = eUR,
            gBP = gBP,
            gHS = gHS,
            jPY = jPY,
            uSD = uSD,
            zAR = zAR,
            aUD = aUD
        )
    }
}