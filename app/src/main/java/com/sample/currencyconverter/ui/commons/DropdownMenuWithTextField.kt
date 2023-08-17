package com.sample.currencyconverter.ui.commons

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sample.currencyconverter.R
import com.sample.currencyconverter.ui.theme.CurrencyConverterTheme
import com.sample.currencyconverter.util.Utility.getFlagEmojiFromCurrencyCode

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownMenuWithTextField(
    onSelectionChange: (String) -> Unit
) {
    val currencies = stringArrayResource(R.array.currency_codes).sortedArray()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(currencies[0]) }

//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .border(1.dp, MaterialTheme.colorScheme.onSurface),
//    ) {


}

@Composable
@ExperimentalMaterial3Api
fun RowScope.ResizableDropdown(
    onSelectionChange: (String) -> Unit
) {

    val currencies = stringArrayResource(R.array.currency_codes).sortedArray()
    var expanded by remember { mutableStateOf(false) }
    var selectedOptionText by remember { mutableStateOf(currencies[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        modifier = Modifier
            .padding(16.dp)
            .wrapContentWidth()
            .weight(1f),
        onExpandedChange = {
            expanded = !expanded
        },
    ) {
        TextField(
            modifier = Modifier.menuAnchor().align(Alignment.CenterVertically),
            value = selectedOptionText,
            readOnly = true,
            onValueChange = { },
            leadingIcon = {
                Text(
                    text = getFlagEmojiFromCurrencyCode(selectedOptionText),
                    modifier = Modifier.wrapContentWidth(Alignment.Start),
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize,
                )
            },
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
                    text = {
                        Text(
                            text = selectedCurrency,
                            modifier = Modifier.wrapContentWidth(Alignment.Start)
                        )
                    },
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


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun PreviewDropdownMenuWithTextField() {
    CurrencyConverterTheme {
        Row {
            ResizableDropdown(onSelectionChange = {})
            ResizableDropdown(onSelectionChange = {})
        }
    }
}