package com.sample.currencyconverter.ui.practice

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.currencyconverter.ui.theme.CurrencyConverterTheme

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        CustomColumn(3F)
        CustomColumn(1F, MaterialTheme.colorScheme.secondary)

    }
}

@Composable
fun ColumnScope.CustomColumn(weight: Float, color: Color = MaterialTheme.colorScheme.primary) {
    Surface(
        modifier = Modifier
            .width(200.dp)
            .weight(weight),
        color = color
    ) { }
}

@Composable
fun MultiColoredBackground() {
    Box(
        modifier = Modifier
            .height(200.dp)
            .width(200.dp)
            .background(Color.White)
            .border(width = 5.dp, color = Color.Red)
            .border(width = 10.dp, color = Color.Yellow)
            .border(width = 15.dp, color = Color.Green)
            .border(width = 20.dp, color = Color.Blue)
            .border(width = 25.dp, color = Color.Red)
            .border(width = 30.dp, color = Color.Yellow)
            .border(width = 35.dp, color = Color.Green)
            .border(width = 40.dp, color = Color.Blue)
            .border(width = 45.dp, color = Color.Red)
            .border(width = 50.dp, color = Color.Yellow)
            .border(width = 55.dp, color = Color.Green)
            .border(width = 60.dp, color = Color.Blue)
            .border(width = 65.dp, color = Color.Red)
            .border(width = 70.dp, color = Color.Yellow)
            .border(width = 75.dp, color = Color.Green)
            .border(width = 80.dp, color = Color.Blue)
            .border(width = 85.dp, color = Color.Red)
            .border(width = 90.dp, color = Color.Yellow)
            .border(width = 95.dp, color = Color.Green)
            .border(width = 100.dp, color = Color.Blue)
    )
}

@Preview(showBackground = true)
@Composable
fun PracticePreview() {
    CurrencyConverterTheme {
        MultiColoredBackground()
    }
}