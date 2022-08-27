package com.example.currencyconverterapp.domain.usecase

import androidx.lifecycle.MutableLiveData
import com.example.currencyconverterapp.data.response.Rates
import com.example.currencyconverterapp.domain.data_interface.CurrencyRemote
import com.example.currencyconverterapp.presentaton.ui.CrrancyHome.CurrencyViewModel
import com.example.currencyconverterapp.presentaton.utils.Resource
import kotlin.math.round

class CurrencyUseCase(private val currencyRemote: CurrencyRemote) {
    suspend operator fun invoke(amountString: String, fromCurrency: String, toCurrency: String,
        _conversion: MutableLiveData<CurrencyViewModel.CurrencyEvent>) {
        val fromAmount = amountString.toFloatOrNull()
        if (fromAmount == null) {
            _conversion.postValue(CurrencyViewModel.CurrencyEvent.Failure("Not a valid amount"))
            return
        }
        when (val ratesResponse = currencyRemote.getRates(fromCurrency)) {
            is Resource.Error -> _conversion.postValue( CurrencyViewModel.CurrencyEvent.Failure(ratesResponse.message!!))

            is Resource.Success -> {
                val rates = ratesResponse.data!!.rates
                val rate = getRateForCurrency(toCurrency, rates)


                if (rate == null) {
                    CurrencyViewModel.CurrencyEvent.Failure("unExpected error")
                } else {

                    val converterCurrency = round(fromAmount * rate * 100) / 100
                    _conversion.postValue( CurrencyViewModel.CurrencyEvent.Success(
                        "$fromAmount $fromCurrency = $converterCurrency $toCurrency"
                    ))
                }
            }

        }
        
    }
    private fun getRateForCurrency(currency: String, rates: Rates) = when (currency) {
        "CAD" -> rates.cAD
        "HKD" -> rates.hKD
        "ISK" -> rates.iSK
        "EUR" -> rates.eUR
        "PHP" -> rates.pHP
        "DKK" -> rates.dKK
        "HUF" -> rates.hUF
        "CZK" -> rates.cZK
        "AUD" -> rates.aUD
        "RON" -> rates.rON
        "SEK" -> rates.sEK
        "IDR" -> rates.iDR
        "INR" -> rates.iNR
        "BRL" -> rates.bRL
        "RUB" -> rates.rUB
        "HRK" -> rates.hRK
        "JPY" -> rates.jPY
        "THB" -> rates.tHB
        "CHF" -> rates.cHF
        "SGD" -> rates.sGD
        "PLN" -> rates.pLN
        "BGN" -> rates.bGN
        "CNY" -> rates.cNY
        "NOK" -> rates.nOK
        "NZD" -> rates.nZD
        "ZAR" -> rates.zAR
        "USD" -> rates.uSD
        "MXN" -> rates.mXN
        "ILS" -> rates.iLS
        "GBP" -> rates.gBP
        "KRW" -> rates.kRW
        "MYR" -> rates.mYR
        else -> null
    }
}
