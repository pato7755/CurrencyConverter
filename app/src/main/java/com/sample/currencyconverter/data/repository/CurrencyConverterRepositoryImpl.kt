package com.sample.currencyconverter.data.repository

import com.sample.currencyconverter.data.remote.CurrencyConverterApi
import com.sample.currencyconverter.domain.model.pair.PairConversionRate
import com.sample.currencyconverter.domain.model.rates.RatesResponse
import com.sample.currencyconverter.domain.repository.CurrencyConverterRepository
import javax.inject.Inject

class CurrencyConverterRepositoryImpl @Inject constructor(
    private val api: CurrencyConverterApi
) : CurrencyConverterRepository {

    override suspend fun getConversionRates(code: String): RatesResponse {
        return api.getConversionRates(code).ratesDtoToDomainMapper()
    }

    override suspend fun getPairConversionRates(
        baseCode: String,
        targetCode: String,
        amount: String?
    ): PairConversionRate {
        return api.getPairConversionRates(baseCode, targetCode, amount)
            .conversionPairDtoToDomainMapper()
    }

}