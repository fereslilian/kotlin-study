package com.example.ktor.app

import com.example.ktor.app.Statistic.Companion
import io.ktor.features.NotFoundException
import org.junit.Assert
import org.junit.Test
import kotlin.test.assertEquals

class StatisticTest {

    @Test
    fun `when getting statistics from an empty Event list, it returns NotFoundException`() {

        Assert.assertThrows(NotFoundException::class.java) {
            Statistic.fromEvents(emptyList())
        }
    }

    @Test
    fun `when getting statistics from a non empty Event list, it returns Statistic`() {
        val yEventOne = 1282509067
        val yEventTwo = 1539565646
        val xEventOne = 0.0442672968
        val xEventTwo = 0.0586780608
        val xList = listOf(xEventOne, xEventTwo)
        val yList = listOf(yEventOne, yEventTwo)
        val events = listOf(
            Event(System.currentTimeMillis(), xEventOne, yEventOne),
            Event(System.currentTimeMillis(), xEventTwo, yEventTwo),
        )

        val statistic = Statistic.fromEvents(events)

        assertEquals(xList.sum(), statistic.sumX)
        assertEquals(events.size, statistic.total)
        assertEquals(xList.average(), statistic.avgX)
        assertEquals(yList.average(), statistic.avgY)
        assertEquals(yList.sum().toLong(), statistic.sumY)
    }
}
