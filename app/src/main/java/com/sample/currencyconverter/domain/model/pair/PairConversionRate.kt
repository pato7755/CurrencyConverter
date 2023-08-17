package com.sample.currencyconverter.domain.model.pair


data class PairConversionRate(
    val baseCode: String,
    val conversionRate: Double,
    val conversionResult: Double?,
    val result: String,
    val targetCode: String,
    val timeLastUpdateUtc: String,
    val timeNextUpdateUtc: String
)