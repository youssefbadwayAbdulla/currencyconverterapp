package com.example.currencyconverterapp.domain.model


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CurrencyModel(
    val name: String,
    val value: Float,
    @SerializedName("base")
    val base: String?=null,
    @SerializedName("end_date")
    val endAt: String?=null,
    @SerializedName("start_date")
    val startAt: String?=null
): Serializable