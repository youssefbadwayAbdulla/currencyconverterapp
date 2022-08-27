package com.example.currencyconverterapp.domain.data_interface

import com.example.currencyconverterapp.data.response.CurrencyApiResponse
import com.example.currencyconverterapp.presentaton.utils.Resource


interface CurrencyRemote {
     suspend fun getRates(base: String): Resource<CurrencyApiResponse>
}