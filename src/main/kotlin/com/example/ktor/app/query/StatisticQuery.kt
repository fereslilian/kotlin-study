package com.example.ktor.app.query

import com.example.ktor.app.Statistic
import com.example.ktor.app.StoredEvents

object StatisticQuery {

    fun perform(): Statistic = Statistic.fromEvents(StoredEvents.getWithinPastSixtySeconds())
}
