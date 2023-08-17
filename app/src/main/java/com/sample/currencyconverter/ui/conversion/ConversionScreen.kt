package com.sample.currencyconverter.ui.conversion

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sample.currencyconverter.R
import com.sample.currencyconverter.ui.commons.ProgressIndicator
import com.sample.currencyconverter.ui.commons.ResizableDropdown

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
fun ConversionScreen(
    viewModel: ConversionViewModel = hiltViewModel()
) {
    val text by viewModel.amountState.collectAsStateWithLifecycle()
    val baseCodeState by viewModel.baseCodeState.collectAsStateWithLifecycle()
    val targetCodeState by viewModel.targetCodeState.collectAsStateWithLifecycle()
    val amountState by viewModel.amountState.collectAsStateWithLifecycle()
    val pairConversionState by viewModel.pairConversionState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Convert",
                    )
                }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                        .wrapContentHeight(Alignment.Top)
            ) {
                ResizableDropdown(
                    onSelectionChange = { baseCode ->
                        viewModel.setBaseCode(baseCode)
                    }
                )
                ResizableDropdown(
                    onSelectionChange = { targetCode ->
                        viewModel.setTargetCode(targetCode)
                    }
                )
            }
            OutlinedTextField(
                modifier = Modifier
                    .width(200.dp)
                    .padding(16.dp),
                value = text.amount?.let { TextFieldValue(it) } ?: TextFieldValue(""),
                label = {
                    Text("Amount")
                },
                onValueChange = { value ->
                    viewModel.setAmount(
                        value.text
                    )
//                    viewModel.setAmount(value.text)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal)

            )

            Button(
                onClick = {
                    viewModel.getPairConversionRates(
                        baseCodeState.baseCode,
                        targetCodeState.targetCode,
                        amountState.amount
                    )
                },
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 20.dp,
                    pressedElevation = 5.dp,
                    disabledElevation = 0.dp
                )
            ) {
                Text(stringResource(R.string.convert))
            }

            pairConversionState.success?.let {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = 100.dp)
                        .padding(16.dp)
                        .background(Color.LightGray)
                        .border(
                            shape = RoundedCornerShape(8.dp),
                            width = 0.dp,
                            color = MaterialTheme.colorScheme.onSurface
                        )
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "${stringResource(id = R.string.one)} ${it.baseCode} " +
                                    "${stringResource(id = R.string.equals)} " +
                                    "${it.conversionRate} ${it.targetCode}",
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )


                    }


                }
            }
        }

    }

    if (pairConversionState.isLoading) {
        ProgressIndicator()
    }

    val context = LocalContext.current
    LaunchedEffect(pairConversionState.error) {
        if (pairConversionState.error != null) {
            Toast.makeText(context, pairConversionState.error, Toast.LENGTH_SHORT).show()
        }
    }

}

@Preview
@Composable
fun PreviewConversionList() {
    ConversionScreen()
}