package com.example.currencyconverterapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.currencyconverterapp.data.dao.CurrencyDao
import com.example.currencyconverterapp.data.entities.CurrencyEntity

@Database(entities = [CurrencyEntity::class], version =4, exportSchema = false)
abstract class CurrencyDatabase :RoomDatabase(){
    abstract fun currencyDao(): CurrencyDao
}
