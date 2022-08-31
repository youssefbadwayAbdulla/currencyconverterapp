package com.example.currencyconverterapp.domain.model

import java.io.Serializable

data class CurrencyModel(
    val name: String,
    val value: Float
): Serializable