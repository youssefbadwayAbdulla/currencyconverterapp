package com.example.currencyconverterapp.domain.usecase

import com.example.currencyconverterapp.data.response.CurrencyApiResponse
import com.example.currencyconverterapp.domain.data_interface.CurrencyLocalRepository
import com.example.currencyconverterapp.domain.data_interface.CurrencyRemoteRepository
import com.example.currencyconverterapp.presentaton.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteCurrencyUseCase(
    private val currencyRemoteRepository: CurrencyRemoteRepository,
    private val currencyLocalRepository: CurrencyLocalRepository
) {
    suspend fun getRates(
        amountString: String,
        fromCurrency: String
    ): Flow<Resource<CurrencyApiResponse>> {
        return flow {
            val fromAmount = amountString.toFloatOrNull()
            val result = currencyLocalRepository.getAllCurrency()
            if (result.isEmpty()) {
                currencyRemoteRepository.getAllData(fromCurrency).body()?.rates
            }
            if (fromAmount == null) {
                emit(Resource.Error("Not a valid amount"))
            } else {
                emit(currencyRemoteRepository.getRates(fromCurrency))
            }
        }

    }
}