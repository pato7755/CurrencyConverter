package com.sample.currencyconverter.ui.rates

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.currencyconverter.common.Resource
import com.sample.currencyconverter.domain.model.rates.RatesAndCodes
import com.sample.currencyconverter.domain.model.rates.Rates
import com.sample.currencyconverter.domain.model.rates.RatesResponse
import com.sample.currencyconverter.domain.usecases.RatesUseCase
import com.sample.currencyconverter.domain.usecases.PairConversionUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class RatesViewModel @Inject constructor(
    private val ratesUseCase: RatesUseCase,
) : ViewModel() {

    private val _ratesState = MutableStateFlow(RatesState())
    val ratesState: StateFlow<RatesState> = _ratesState.asStateFlow()

    fun getConversionRates(selectedCode: String) {
        viewModelScope.launch(Dispatchers.IO) {
            ratesUseCase.invoke(selectedCode).collectLatest { result ->
                when (result) {
                    // Loading
                    is Resource.Loading -> {
                        _ratesState.update { state ->
                            state.copy(isLoading = true)
                        }
                    }
                    // Success
                    is Resource.Success -> {
                        Timber.d("result - ${result.data}")
                        _ratesState.update { state ->
                            state.copy(
                                isLoading = false,
                                success = RatesResponse(
                                    baseCode = result.data!!.baseCode,
                                    rates = result.data.rates,
                                    result = result.data.result,
                                    timeLastUpdateUtc = result.data.timeLastUpdateUtc,
                                    timeNextUpdateUtc = result.data.timeNextUpdateUtc,
                                    ratesAndCodes = result.data.ratesAndCodes
                                )
                            )
                        }
                        Timber.d(_ratesState.value.success?.rates.toString())
                    }
                    // Error
                    is Resource.Error -> {
                        println("Error occurred - ${result.message}")
                        _ratesState.update { state ->
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