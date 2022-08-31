package com.example.currencyconverterapp.data.repositories

import android.util.Log
import com.example.currencyconverterapp.data.dao.CurrencyDao
import com.example.currencyconverterapp.data.local.CurrencyDatabase
import com.example.currencyconverterapp.data.mapper.mapToEntityList
import com.example.currencyconverterapp.data.remote.CurrencyAPI
import com.example.currencyconverterapp.data.response.CurrencyApiResponse
import com.example.currencyconverterapp.domain.data_interface.CurrencyRemoteRepository
import com.example.currencyconverterapp.presentaton.utils.Resource
import com.google.gson.Gson

class CurrencyRemoteIMP(
    private val api: CurrencyAPI,
    private val currencyDatabase: CurrencyDatabase
) : CurrencyRemoteRepository {
    override suspend fun getRates(base: String): Resource<CurrencyApiResponse> {
        return try {
            val response = api.getRates(base)
            val result = response.body()
            Log.v("DataResult", "ResultIs:" + Gson().toJson(result).toString())
            if (result != null) {
                currencyDatabase.currencyDao()
                    .insertOrUpdateUser(result.rates.getRates().mapToEntityList())
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }

        } catch (e: Exception) {
            Log.v("DataResult", e.message.toString())

            Resource.Error(e.message ?: "An error ")
        }
    }

    override suspend fun getDataRates(
        startData: String,
        endDate: String,
        base: String
    ): Resource<CurrencyApiResponse> {
        return try {
            val response = api.getDataRates(startData, endDate, base)
            val result = response.body()
            if (result != null) {
                Resource.Success(result)
            } else {
                Resource.Error(response.message())
            }
        } catch (e: Exception) {
            Log.v("DataResult", e.message.toString())

            Resource.Error(e.message ?: "An error ")
        }
    }
}