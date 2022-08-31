package com.example.currencyconverterapp.domain.usecase

import android.util.Log
import com.example.currencyconverterapp.data.entities.CurrencyEntity
import com.example.currencyconverterapp.domain.data_interface.CurrencyLocalRepository
import com.example.currencyconverterapp.domain.model.CurrencyModel
import com.example.currencyconverterapp.presentaton.utils.DateUtils

class LocalWeekUseCase(private val currencyLocalRepository: CurrencyLocalRepository)
{
    suspend operator fun invoke(): List<CurrencyModel> {
        val getWeek = DateUtils.getEndDate()
        Log.i("viewModel", "invoke:$getWeek ")
        return currencyLocalRepository.getWeekAsteroids(getWeek).map {
            it.convertToDataModel()
        }.toList()
    }
}