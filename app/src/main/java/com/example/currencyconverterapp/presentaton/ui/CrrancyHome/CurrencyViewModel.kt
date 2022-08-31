package com.example.currencyconverterapp.presentaton.ui.CrrancyHome

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterapp.data.response.CurrencyApiResponse
import com.example.currencyconverterapp.data.response.Rates
import com.example.currencyconverterapp.domain.model.CurrencyModel
import com.example.currencyconverterapp.domain.usecase.LocalDayUseCase
import com.example.currencyconverterapp.domain.usecase.LocalWeekUseCase
import com.example.currencyconverterapp.domain.usecase.RemoteCurrencyUseCase
import com.example.currencyconverterapp.presentaton.utils.Resource
import com.example.currencyconverterapp.presentaton.utils.Resource.Error
import com.example.currencyconverterapp.presentaton.utils.Resource.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.round


class CurrencyViewModel(
    private val currencyUseCase: RemoteCurrencyUseCase,
    private val localDayUseCase: LocalDayUseCase,
    private val localWeekUseCase: LocalWeekUseCase

) : ViewModel() {
    private val _currencyEvent = MutableLiveData<CurrencyEvent<String>>(CurrencyEvent.Empty())
    val currencyEvent: LiveData<CurrencyEvent<String>> = _currencyEvent

    private val currencyMutableList = MutableLiveData<List<CurrencyModel>>()
    val currencyLiveData: LiveData<List<CurrencyModel>> get() = currencyMutableList

//    //Local Database Room
//    private var currencyMutableLiveData = MutableLiveData<List<CurrencyEntity>>()
//    val currencyLiveData: LiveData<List<CurrencyEntity>> get() = currencyMutableLiveData

    private val _currencyList = MutableLiveData<List<CurrencyModel>>(listOf())
    val currencyList = _currencyList

    fun getRates(amountString: String, fromCurrency: String, toCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _currencyEvent.postValue(CurrencyEvent.Loading())
            currencyUseCase.getRates(amountString, fromCurrency).collect {
                collectGetRates(amountString, fromCurrency, toCurrency, it)
            }
        }
    }


    fun getDataDay() = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) { localDayUseCase() }
        Log.d("viewModel", "$result")
        currencyMutableList.postValue(result)
    }

    fun getDataWeekAndSaved() = viewModelScope.launch {
        val result = withContext(Dispatchers.IO) { localWeekUseCase() }
        Log.d("viewModel", "$result")
        currencyMutableList.postValue(result)
    }

    private fun collectGetRates(
        amountString: String,
        fromCurrency: String,
        toCurrency: String,
        result: Resource<CurrencyApiResponse>
    ) {
        when (result) {
            is Success -> {
                handleSuccessState(amountString, fromCurrency, toCurrency, result)
            }
            is Error -> handleFailState(result)

        }
    }

    private fun handleFailState(data: Error<CurrencyApiResponse>) {
        _currencyEvent.postValue(CurrencyEvent.Failure(data.message ?: ""))
    }

    private fun handleSuccessState(
        amountString: String,
        fromCurrency: String,
        toCurrency: String,
        result: Success<CurrencyApiResponse>
    ) {
        val rate = getRateForCurrency(toCurrency, result.data?.rates)
        val fromAmount = amountString.toFloatOrNull() ?: 0f
        rate?.let {
            val converterCurrency = round(fromAmount * rate * 100) / 100
            val finalResult = "$fromAmount $fromCurrency = $converterCurrency $toCurrency"
            _currencyEvent.postValue(CurrencyEvent.Success(finalResult))
        }
        _currencyList.postValue(result.data?.rates?.getRates() ?: listOf())
    }

    private fun getRateForCurrency(currency: String, rates: Rates?) = when (currency) {
        "CAD" -> rates?.cAD
        "HKD" -> rates?.hKD
        "ISK" -> rates?.iSK
        "EUR" -> rates?.eUR
        "PHP" -> rates?.pHP
        "DKK" -> rates?.dKK
        "HUF" -> rates?.hUF
        "CZK" -> rates?.cZK
        "AUD" -> rates?.aUD
        "RON" -> rates?.rON
        "SEK" -> rates?.sEK
        "IDR" -> rates?.iDR
        "INR" -> rates?.iNR
        "BRL" -> rates?.bRL
        "RUB" -> rates?.rUB
        "HRK" -> rates?.hRK
        "JPY" -> rates?.jPY
        "THB" -> rates?.tHB
        "CHF" -> rates?.cHF
        "SGD" -> rates?.sGD
        "PLN" -> rates?.pLN
        "BGN" -> rates?.bGN
        "CNY" -> rates?.cNY
        "NOK" -> rates?.nOK
        "NZD" -> rates?.nZD
        "ZAR" -> rates?.zAR
        "USD" -> rates?.uSD
        "MXN" -> rates?.mXN
        "ILS" -> rates?.iLS
        "GBP" -> rates?.gBP
        "KRW" -> rates?.kRW
        "MYR" -> rates?.mYR
        else -> null
    }

    sealed class CurrencyEvent<T> {
        class Success<T>(val data: T) : CurrencyEvent<T>()
        class Failure<T>(val errorMessage: String) : CurrencyEvent<T>()
        class Loading<T> : CurrencyEvent<T>()
        class Empty<T> : CurrencyEvent<T>()
    }

}


