package com.sample.currencyconverter.domain.repository

import com.sample.currencyconverter.domain.model.pair.PairConversionRate
import com.sample.currencyconverter.domain.model.rates.RatesResponse

interface CurrencyConverterRepository {

    suspend fun getConversionRates(code: String): RatesResponse

    suspend fun getPairConversionRates(
        baseCode: String,
        targetCode: String,
        amount: String?
    ): PairConversionRate
}