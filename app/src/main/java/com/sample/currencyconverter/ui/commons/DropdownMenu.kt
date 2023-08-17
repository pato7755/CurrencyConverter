package com.sample.currencyconverter.ui.commons

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.sample.currencyconverter.R
import com.sample.currencyconverter.ui.rates.RatesViewModel
import com.sample.currencyconverter.ui.theme.CurrencyConverterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenu(
    onSelectionChange: (String) -> Unit
) {
    val currencies = stringArrayResource(R.array.currency_codes).sortedArray()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(currencies[0]) }
    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = Modifier
            .padding(16.dp),
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        TextField(
            modifier = Modifier.menuAnchor(),
            value = selectedOptionText,
            readOnly = true,
            label = {
                Text(stringResource(R.string.currencies))
            },
            onValueChange = { },
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors()
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }) {
            currencies.forEach { selectedCurrency ->
                DropdownMenuItem(
                    text = { Text(selectedCurrency) },
                    onClick = {
                        selectedOptionText = selectedCurrency
                        expanded = false
                        onSelectionChange(selectedOptionText)
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    CurrencyConverterTheme {
        DropdownMenu(onSelectionChange = {})
    }
}