package com.example.currencyconverterapp.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.currencyconverterapp.data.response.Rates
import com.example.currencyconverterapp.domain.model.CurrencyModel
import com.example.currencyconverterapp.presentaton.utils.DateUtils
import com.google.gson.annotations.SerializedName

@Entity(tableName = "currency_entity")
class CurrencyEntity (
    @PrimaryKey()
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "value")
    val value: Float,
    @SerializedName("base")
    val base: String?=null,
    @SerializedName("end_date")
    val endAt: String?=null,

    @SerializedName("start_date")
    val startAt: String?=null

){
    fun convertToDataModel() = CurrencyModel(
        name = name,
        value=value,
        base=base, endAt=endAt, startAt=startAt

    )
}