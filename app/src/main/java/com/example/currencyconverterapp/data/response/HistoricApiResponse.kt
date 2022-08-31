package com.example.currencyconverterapp.data.response

import com.google.gson.annotations.SerializedName

data class HistoricApiResponse(
    @SerializedName("base")
    val base: String,
    @SerializedName("end_date")
    val endAt: String,
    @SerializedName("rates")
//    val rates: Map<String, Map<String, Double>>,
    val rates: Rates,
    @SerializedName("start_date")
    val startAt: String
)