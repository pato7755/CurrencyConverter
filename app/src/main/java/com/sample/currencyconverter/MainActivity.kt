package com.sample.currencyconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.sample.currencyconverter.ui.MainScreen
import com.sample.currencyconverter.ui.rates.RatesScreen
import com.sample.currencyconverter.ui.theme.CurrencyConverterTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CurrencyConverterTheme {
                MainScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CurrencyConverterTheme {
        RatesScreen()
    }
}