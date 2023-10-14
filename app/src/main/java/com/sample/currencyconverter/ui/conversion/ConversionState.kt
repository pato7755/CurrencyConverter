package com.sample.currencyconverter.ui.conversion

import com.sample.currencyconverter.domain.model.pair.PairConversionRate

data class ConversionState(
    val isLoading: Boolean = false,
    val success: PairConversionRate? = null,
    val error: String? = null
)
