package com.sample.currencyconverter.data.remote.dto.pair


import com.google.gson.annotations.SerializedName
import com.sample.currencyconverter.domain.model.pair.PairConversionRate

data class PairConversionRateDto(
    @SerializedName("base_code")
    val baseCode: String,
    @SerializedName("conversion_rate")
    val conversionRate: Double,
    @SerializedName("conversion_result")
    val conversionResult: Double,
    @SerializedName("documentation")
    val documentation: String,
    @SerializedName("result")
    val result: String,
    @SerializedName("target_code")
    val targetCode: String,
    @SerializedName("terms_of_use")
    val termsOfUse: String,
    @SerializedName("time_last_update_unix")
    val timeLastUpdateUnix: Int,
    @SerializedName("time_last_update_utc")
    val timeLastUpdateUtc: String,
    @SerializedName("time_next_update_unix")
    val timeNextUpdateUnix: Int,
    @SerializedName("time_next_update_utc")
    val timeNextUpdateUtc: String
) {
    fun conversionPairDtoToDomainMapper(): PairConversionRate {
        return PairConversionRate(
            baseCode = baseCode,
            conversionRate = conversionRate,
            conversionResult = conversionResult,
            result = result,
            targetCode = targetCode,
            timeLastUpdateUtc = timeLastUpdateUtc,
            timeNextUpdateUtc = timeNextUpdateUtc
        )
    }
}