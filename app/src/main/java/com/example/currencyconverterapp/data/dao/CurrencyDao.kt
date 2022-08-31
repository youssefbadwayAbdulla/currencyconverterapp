package com.example.currencyconverterapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyconverterapp.data.entities.CurrencyEntity
@Dao
interface CurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUser(data: List<CurrencyEntity>)

    @Query("SELECT * FROM  currency_entity")
    suspend fun getAllCurrency(): List<CurrencyEntity>


    @Query("DELETE FROM currency_entity")
    suspend fun deleteAllDataFromDatabase()

    @Query("SELECT * FROM  currency_entity WHERE startAt == :today ORDER by startAt ASC ")
    suspend fun getCurrencyToday(today:String):List<CurrencyEntity>


    @Query("SELECT * FROM currency_entity WHERE endAt <= :week ORDER by endAt ASC")
    suspend fun getWeekAsteroids(week: String) :List<CurrencyEntity>
}