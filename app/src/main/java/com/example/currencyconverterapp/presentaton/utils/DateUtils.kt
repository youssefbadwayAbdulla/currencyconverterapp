package com.example.currencyconverterapp.presentaton.utils

import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class DateUtils {
    companion object{

        fun getStartDate(): String {
            return SimpleDateFormat("yyyy-MM-dd").format(Date())
        }

        fun getEndDate(): String {
            val calendar = Calendar.getInstance()
            calendar.add(Calendar.DATE, +7)
            val nextWeek = calendar.time
            return SimpleDateFormat("yyyy-MM-dd").format(nextWeek)
        }
    }
}