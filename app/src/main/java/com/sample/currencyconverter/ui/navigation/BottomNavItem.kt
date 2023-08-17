package com.sample.currencyconverter.ui.navigation

import com.sample.currencyconverter.R

sealed class BottomNavItem(val title: String, val icon: Int, val route: String) {
    object RatesScreen : BottomNavItem("Rates", R.drawable.ic_rates, "rates")
    object ConvertScreen : BottomNavItem("Convert", R.drawable.ic_convert, "convert")
    object SettingsScreen : BottomNavItem("Settings", R.drawable.ic_settings, "settings" )
}
