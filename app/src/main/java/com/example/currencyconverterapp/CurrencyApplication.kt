package com.example.currencyconverterapp

import android.app.Application
import com.example.currencyconverterapp.di.databaseModel
import com.example.currencyconverterapp.di.repositoryModel
import com.example.currencyconverterapp.di.serviceAPIModule
import com.example.currencyconverterapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class CurrencyApplication : Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CurrencyApplication)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModel,
                    serviceAPIModule,
                    databaseModel


                )
            )
        }
    }
}