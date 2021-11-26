package com.example.ktor.app

import io.ktor.features.NotFoundException

data class Statistic(
    val total: Int,
    val sumX: Double,
    val avgX: Double,
    val sumY: Long,
    val avgY: Double
) {
    override fun toString(): String {
        return "$total,$sumX,$avgX,$sumY,$avgY"
    }

    companion object {

        fun fromEvents(events: List<Event>): Statistic {

            if (events.isEmpty())
                throw NotFoundException("No statistic for last period!")

            val yList: List<Int> = events.map { event -> event.y }
            val xList: List<Double> = events.map { event -> event.x }

            return Statistic(
                sumX = xList.sum(),
                total = events.size,
                avgX = xList.average(),
                avgY = yList.average(),
                sumY = yList.sum().toLong(),
            )
        }

    }
}
