package com.sample.currencyconverter.domain.usecases

import com.sample.currencyconverter.common.Resource
import com.sample.currencyconverter.domain.model.pair.PairConversionRate
import com.sample.currencyconverter.domain.repository.CurrencyConverterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PairConversionUseCase @Inject constructor(
    private val repository: CurrencyConverterRepository
) {

    operator fun invoke(baseCode: String, targetCode: String, amount: String?)
            : Flow<Resource<PairConversionRate>> = flow {
        emit(Resource.Loading())
        try {
            emit(
                Resource.Success<PairConversionRate>(
                    repository.getPairConversionRates(
                        baseCode,
                        targetCode,
                        amount
                    )
                )
            )
        } catch (ex: HttpException) {
            emit(
                Resource.Error<PairConversionRate>(
                    ex.localizedMessage ?: "An error occurred while fetching conversion rate"
                )
            )
        } catch (ex: IOException) {
            emit(Resource.Error<PairConversionRate>("Couldn't reach server"))
        }
    }

}