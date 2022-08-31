package com.example.currencyconverterapp.data.repositories

import com.example.currencyconverterapp.data.entities.CurrencyEntity
import com.example.currencyconverterapp.data.local.CurrencyDatabase
import com.example.currencyconverterapp.domain.data_interface.CurrencyLocalRepository

class CurrencyLocalIMP(private val currencyDatabase: CurrencyDatabase):CurrencyLocalRepository {
    override suspend fun insertOrUpdateUser(data: List<CurrencyEntity>) {
        return currencyDatabase.currencyDao().insertOrUpdateUser(data)
    }

    override suspend fun getAllCurrency(): List<CurrencyEntity> {
        return currencyDatabase.currencyDao().getAllCurrency()
    }

    override suspend fun deleteAllDataFromDatabase() {
       return currencyDatabase.currencyDao().deleteAllDataFromDatabase()
    }

    override suspend fun getCurrencyToday(today:String): List<CurrencyEntity> {
        return currencyDatabase.currencyDao().getCurrencyToday(today)
    }

    override suspend fun getWeekAsteroids(week: String): List<CurrencyEntity> {
        return currencyDatabase.currencyDao().getWeekAsteroids(week)
    }

}