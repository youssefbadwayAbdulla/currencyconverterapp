package com.example.currencyconverterapp.domain.usecase

import com.example.currencyconverterapp.domain.data_interface.CurrencyLocalRepository
import com.example.currencyconverterapp.domain.model.CurrencyModel
import com.example.currencyconverterapp.presentaton.utils.DateUtils

class LocalDayUseCase(private val currencyLocalRepository: CurrencyLocalRepository) {
    suspend operator fun invoke(): List<CurrencyModel> {
        val getDay = DateUtils.getStartDate()
        return currencyLocalRepository.getCurrencyToday(getDay).map { it.convertToDataModel() }.toList()
    }
}