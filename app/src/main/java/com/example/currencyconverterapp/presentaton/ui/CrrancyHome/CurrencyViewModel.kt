package com.example.currencyconverterapp.presentaton.ui.CrrancyHome

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.currencyconverterapp.domain.data_interface.CurrencyRemote
import com.example.currencyconverterapp.domain.model.CurrencyModel
import com.example.currencyconverterapp.domain.usecase.CurrencyUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CurrencyViewModel(private val currencyUseCase: CurrencyUseCase) : ViewModel() {
    sealed class CurrencyEvent {
        class Success(val resultText: String) : CurrencyEvent()
        class Failure(val errorMessage: String) : CurrencyEvent()
        object Loading : CurrencyEvent()
        object Empty : CurrencyEvent()
    }

    private val _conversion = MutableLiveData<CurrencyEvent>(CurrencyEvent.Empty)
    val conversion: LiveData<CurrencyEvent> = _conversion
    private val _conversionList = MutableLiveData<List<CurrencyModel>>()
    val conversionList: LiveData<List<CurrencyModel>> = _conversionList

    fun convert(amountString: String, fromCurrency: String, toCurrency: String) {
        viewModelScope.launch(Dispatchers.IO) {
            _conversion.postValue(CurrencyEvent.Loading)
            currencyUseCase(amountString, fromCurrency, toCurrency, _conversion)
          //  _conversionList.postValue()

        }

    }


}