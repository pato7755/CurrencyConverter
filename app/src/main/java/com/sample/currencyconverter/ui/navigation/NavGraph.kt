package com.sample.currencyconverter.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.sample.currencyconverter.ui.conversion.ConversionScreen
import com.sample.currencyconverter.ui.rates.RatesScreen
import com.sample.currencyconverter.ui.settings.SettingsScreen

@Composable
fun NavGraph(navHostController: NavHostController) {
    NavHost(navController = navHostController, startDestination = BottomNavItem.RatesScreen.route, builder = {
        composable(route = BottomNavItem.RatesScreen.route) {
            RatesScreen()
        }
        composable(route = BottomNavItem.ConvertScreen.route) {
            ConversionScreen()
        }
        composable(route = BottomNavItem.SettingsScreen.route) {
            SettingsScreen()
        }
    })
}