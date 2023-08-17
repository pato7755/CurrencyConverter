package com.sample.currencyconverter.di

import com.sample.currencyconverter.common.Constants.BASE_URL
import com.sample.currencyconverter.data.remote.CurrencyConverterApi
import com.sample.currencyconverter.data.repository.CurrencyConverterRepositoryImpl
import com.sample.currencyconverter.domain.repository.CurrencyConverterRepository
import com.sample.currencyconverter.domain.usecases.RatesUseCase
import com.sample.currencyconverter.domain.usecases.PairConversionUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideCurrencyConverterApi(): CurrencyConverterApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CurrencyConverterApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyConverterApi): CurrencyConverterRepository {
        return CurrencyConverterRepositoryImpl(api)
    }

    @Provides
    @Singleton
    fun provideFetchConversionRatesUseCase(repository: CurrencyConverterRepository): RatesUseCase {
        return RatesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideFetchPairConversionRatesUseCase(repository: CurrencyConverterRepository): PairConversionUseCase {
        return PairConversionUseCase(repository)
    }

}