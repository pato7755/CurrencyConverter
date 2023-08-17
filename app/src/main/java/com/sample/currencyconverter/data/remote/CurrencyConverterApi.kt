package com.sample.currencyconverter.data.remote

import com.sample.currencyconverter.common.Constants.API_KEY
import com.sample.currencyconverter.data.remote.dto.pair.PairConversionRateDto
import com.sample.currencyconverter.data.remote.dto.rates.RatesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CurrencyConverterApi {

    @GET("$API_KEY/latest/{code}")
    suspend fun getConversionRates(@Path("code") code: String): RatesResponseDto

    @GET("$API_KEY/pair/{baseCode}/{targetCode}/{amount}")
    suspend fun getPairConversionRates(
        @Path("baseCode") baseCode: String,
        @Path("targetCode") targetCode: String,
        @Path("amount") amount: String?
    ): PairConversionRateDto

}