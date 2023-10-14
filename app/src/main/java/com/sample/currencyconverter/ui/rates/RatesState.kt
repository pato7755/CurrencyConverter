package com.sample.currencyconverter.ui.rates

import com.sample.currencyconverter.domain.model.rates.RatesResponse

data class RatesState(
    val isLoading: Boolean = false,
    val success: RatesResponse? = null,
    val error: String? = null
)
