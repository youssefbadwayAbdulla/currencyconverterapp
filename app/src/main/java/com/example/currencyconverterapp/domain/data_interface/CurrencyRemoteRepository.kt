package com.example.currencyconverterapp.domain.data_interface

import com.example.currencyconverterapp.data.response.CurrencyApiResponse
import com.example.currencyconverterapp.data.response.HistoricApiResponse
import com.example.currencyconverterapp.presentaton.utils.Resource
import retrofit2.Response
import retrofit2.http.Query


interface CurrencyRemoteRepository {
     suspend fun getRates(base: String): Resource<CurrencyApiResponse>
     suspend fun getAllData(base: String): Response<HistoricApiResponse>
}