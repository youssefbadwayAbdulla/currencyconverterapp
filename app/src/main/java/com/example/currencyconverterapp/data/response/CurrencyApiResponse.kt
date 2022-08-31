package com.example.currencyconverterapp.data.response

data class CurrencyApiResponse(
    val base: String?=null,
    val date: String?=null,
    val rates: Rates,

//
//    val rates: Map<String, Double>,
)