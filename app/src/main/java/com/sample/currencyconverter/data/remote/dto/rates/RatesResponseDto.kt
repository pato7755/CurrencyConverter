package com.sample.currencyconverter.data.remote.dto.rates


import com.google.gson.annotations.SerializedName
import com.sample.currencyconverter.domain.model.rates.RatesResponse

data class RatesResponseDto(
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("conversion_rates")
    val conversionRates: ConversionRates,
    @SerializedName("result")
    val result: String,
    @SerializedName("time_last_update_unix")
    val timeLastUpdateUnix: Int,
    @SerializedName("time_last_update_utc")
    val timeLastUpdateUtc: String,
    @SerializedName("time_next_update_unix")
    val timeNextUpdateUnix: Int,
    @SerializedName("time_next_update_utc")
    val timeNextUpdateUtc: String
) {
    fun ratesDtoToDomainMapper(): RatesResponse {
        return RatesResponse(
            baseCode = baseCode,
            rates = conversionRates.dtoToDomainMapper(),
            result = result,
            timeLastUpdateUtc = timeLastUpdateUtc,
            timeNextUpdateUtc = timeNextUpdateUtc,
            ratesAndCodes = emptyList()
        )
    }
}