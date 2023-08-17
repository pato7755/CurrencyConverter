package com.sample.currencyconverter.domain.model.rates


data class RatesResponse(
    val baseCode: String,
    val rates: Rates,
    val result: String,
    val timeLastUpdateUtc: String,
    val timeNextUpdateUtc: String,
    var ratesAndCodes: List<RatesAndCodes>
)

data class RatesAndCodes(
    val code: String,
    val rate: Double
)