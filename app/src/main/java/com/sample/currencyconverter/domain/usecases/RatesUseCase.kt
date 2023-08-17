package com.sample.currencyconverter.domain.usecases

import com.sample.currencyconverter.common.Resource
import com.sample.currencyconverter.domain.model.rates.RatesAndCodes
import com.sample.currencyconverter.domain.model.rates.RatesResponse
import com.sample.currencyconverter.domain.repository.CurrencyConverterRepository
import com.sample.currencyconverter.util.Utility.getCurrencyCodeList
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class RatesUseCase @Inject constructor(
    private val repository: CurrencyConverterRepository
) {

    operator fun invoke(code: String): Flow<Resource<RatesResponse>> = flow {
        try {
            emit(Resource.Loading())
            val rates = repository.getConversionRates(code)
            val ratesAndCodes = getCurrencyCodeList(rates.rates)
            rates.ratesAndCodes = ratesAndCodes
            emit(Resource.Success<RatesResponse>(rates))
        } catch (ex: HttpException) {
            emit(Resource.Error<RatesResponse>(ex.localizedMessage ?:
            "An error occurred while fetching conversion rates"))
        } catch (ex: IOException) {
            emit(Resource.Error<RatesResponse>(ex.localizedMessage ?: "Couldn't reach server"))
        }
    }

}