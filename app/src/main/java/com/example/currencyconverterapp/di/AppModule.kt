package com.example.currencyconverterapp.di

import android.util.Log
import com.example.currencyconverterapp.data.remote.CurrencyAPI
import com.example.currencyconverterapp.data.repositories.CurrencyRemoteIMP
import com.example.currencyconverterapp.domain.data_interface.CurrencyRemote
import com.example.currencyconverterapp.domain.usecase.CurrencyUseCase
import com.example.currencyconverterapp.presentaton.ui.CrrancyHome.CurrencyViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.exchangerate.host/"

val viewModelModule = module {
    viewModel { CurrencyViewModel(
      currencyUseCase = get()
    ) }
}


val repositoryModel: Module = module {
    single<CurrencyRemote> { CurrencyRemoteIMP(api = get()) }
    single { CurrencyUseCase(currencyRemote = get()) }
}

// this part is Retrofit API.....................
val serviceAPIModule: Module = module {
    fun getRetroBuilder(): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }
    single { getRetroBuilder() }
    Log.d("AppModel", "${getRetroBuilder()}")
    fun getAPIServicesInstance(retrofit: Retrofit): CurrencyAPI {
        Log.d("AppModel", "${retrofit.create(CurrencyAPI::class.java)}")

        return retrofit.create(CurrencyAPI::class.java)
    }
    single { getAPIServicesInstance(retrofit = get()) }
}