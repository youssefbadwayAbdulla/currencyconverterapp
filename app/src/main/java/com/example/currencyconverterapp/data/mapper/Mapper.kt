package com.example.currencyconverterapp.data.mapper

import com.example.currencyconverterapp.data.entities.CurrencyEntity
import com.example.currencyconverterapp.data.response.Rates

import com.example.currencyconverterapp.domain.model.CurrencyModel

fun List<CurrencyModel>.mapToEntityList()=this.map {
    CurrencyEntity( name = it.name, value = it.value)
}
