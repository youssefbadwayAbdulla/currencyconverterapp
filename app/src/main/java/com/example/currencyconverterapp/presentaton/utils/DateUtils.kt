package com.example.currencyconverterapp.presentaton.utils

import java.time.LocalDate

class DateUtils {
    companion object{
        fun getEndDate(): String {
            return LocalDate.now().toString()
        }

        fun getStartDate(): String {
            return LocalDate.now().minusDays(30).toString()
        }
    }
}