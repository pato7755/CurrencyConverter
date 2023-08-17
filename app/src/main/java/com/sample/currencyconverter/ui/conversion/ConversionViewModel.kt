package com.sample.currencyconverter.ui.conversion

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.currencyconverter.common.Resource
import com.sample.currencyconverter.domain.model.pair.PairConversionRate
import com.sample.currencyconverter.domain.usecases.PairConversionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class ConversionViewModel @Inject constructor(
    private val pairConversionUseCase: PairConversionUseCase
) : ViewModel() {

    private val _pairConversionState = MutableStateFlow(ConversionState())
    val pairConversionState = _pairConversionState.asStateFlow()

    private val _baseCodeState = MutableStateFlow(BaseCodeState())
    val baseCodeState = _baseCodeState.asStateFlow()

    private val _targetCodeState = MutableStateFlow(TargetCodeState())
    val targetCodeState = _targetCodeState.asStateFlow()

    private val _amountState = MutableStateFlow(AmountState())
    val amountState = _amountState.asStateFlow()

    fun setBaseCode(baseCode: String) {
        _baseCodeState.update { state ->
            state.copy(
                baseCode = baseCode
            )
        }
    }

    fun setTargetCode(targetCode: String) {
        _targetCodeState.update { state ->
            state.copy(
                targetCode = targetCode
            )
        }
    }

    fun setAmount(amount: String?) {
        _amountState.update {state ->
            state.copy(
                amount = amount
            )
        }
    }

    fun getPairConversionRates(baseCode: String, targetCode: String, amount: String?) {
        viewModelScope.launch(Dispatchers.IO) {
            pairConversionUseCase.invoke(baseCode, targetCode, amount).collectLatest { result ->
                when (result) {
                    is Resource.Loading -> {
                        _pairConversionState.value = ConversionState(isLoading = true)
                    }

                    is Resource.Success -> {
                        Timber.d("result - ${result.data}")
                        _pairConversionState.update { state ->
                            state.copy(
                                isLoading = false,
                                success = PairConversionRate(
                                    baseCode = result.data!!.baseCode,
                                    conversionRate = result.data.conversionRate,
                                    conversionResult = result.data.conversionResult,
                                    result = result.data.result,
                                    targetCode = result.data.targetCode,
                                    timeLastUpdateUtc = result.data.timeLastUpdateUtc,
                                    timeNextUpdateUtc = result.data.timeNextUpdateUtc
                                )
                            )
                        }
                    }

                    is Resource.Error -> {
                        _pairConversionState.update { state ->
                            state.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                }
            }
        }
    }


}