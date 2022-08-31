package com.example.currencyconverterapp.domain.data_interface

import com.example.currencyconverterapp.data.entities.CurrencyEntity

interface CurrencyLocalRepository {
    suspend fun insertOrUpdateUser(data: List<CurrencyEntity>)
    suspend fun getAllCurrency(): List<CurrencyEntity>
    suspend fun deleteAllDataFromDatabase()
    suspend fun getCurrencyToday(today:String):List<CurrencyEntity>
    suspend fun getWeekAsteroids(week: String) :List<CurrencyEntity>
}