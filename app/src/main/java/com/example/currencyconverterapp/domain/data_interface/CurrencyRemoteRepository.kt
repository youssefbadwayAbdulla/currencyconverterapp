package com.example.currencyconverterapp.domain.data_interface

import com.example.currencyconverterapp.data.response.CurrencyApiResponse
import com.example.currencyconverterapp.presentaton.utils.Resource
import retrofit2.http.Query


interface CurrencyRemoteRepository {
     suspend fun getRates(base: String): Resource<CurrencyApiResponse>
     suspend fun getDataRates(startData: String, endDate: String, base: String): Resource<CurrencyApiResponse>

}