package com.sample.currencyconverter.ui.conversion

data class BaseCodeState(
    val baseCode: String = "",
)

data class TargetCodeState(
    val targetCode: String = "",
)

data class AmountState(
    val amount: String? = null,
)