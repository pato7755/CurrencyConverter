package com.sample.currencyconverter.ui.rates

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.currencyconverter.R
import com.sample.currencyconverter.domain.model.rates.RatesAndCodes
import com.sample.currencyconverter.ui.commons.ProgressIndicator
import com.sample.currencyconverter.ui.commons.ResizableDropdown
import com.sample.currencyconverter.util.Utility.getFlagEmojiFromCurrencyCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RatesScreen(
    viewModel: RatesViewModel = hiltViewModel()
) {
    val state by viewModel.ratesState.collectAsStateWithLifecycle()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "View Rates",
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            Row(
                horizontalArrangement = Arrangement.Center
            ) {
                ResizableDropdown(onSelectionChange = { code ->
                    viewModel.getConversionRates(code)
                })
            }

            Spacer(modifier = Modifier.height(20.dp))
            RatesList(state)
        }

        if (state.isLoading) {
            ProgressIndicator()
        }

        val context = LocalContext.current
        LaunchedEffect(state.error) {
            if (state.error != null) {
                Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
            }
        }
    }
}

@Composable
fun RatesList(
    state: RatesState
) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        contentPadding = PaddingValues(16.dp),
    ) {
        state.success?.let {
            items(it.ratesAndCodes) { ratesAndCodes ->
                RatesItem(
                    ratesAndCodes = ratesAndCodes,
                    state = state
                )
                Divider(color = Color.LightGray, thickness = 1.dp)
            }
        }
    }
}

@Composable
fun RatesItem(
    ratesAndCodes: RatesAndCodes,
    state: RatesState
) {
    Row(
        modifier = Modifier.padding(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = getFlagEmojiFromCurrencyCode(state.success?.baseCode ?: ""),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .absolutePadding(bottom = 15.dp)
        )
        Text(
            text = getFlagEmojiFromCurrencyCode(ratesAndCodes.code),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .absolutePadding(top = 15.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        Column(
            modifier = Modifier
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = MaterialTheme.typography.bodyLarge,
                text = "${state.success?.baseCode} "
                        + stringResource(id = R.string.to)
                        + " ${ratesAndCodes.code}"
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                color = Color.Gray,
                text = "${stringResource(id = R.string.one)} "
                        + "${state.success?.baseCode} "
                        + stringResource(id = R.string.equals)
                        + " ${ratesAndCodes.rate}"
                        + " ${ratesAndCodes.code}"
            )

        }
    }
}

@Preview
@Composable
fun PreviewRatesList() {
    RatesScreen()
}