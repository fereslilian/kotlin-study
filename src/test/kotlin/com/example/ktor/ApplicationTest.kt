package com.example.ktor

import com.example.ktor.app.Statistic
import com.example.ktor.app.command.EventsStorage
import com.example.ktor.app.query.StatisticQuery
import com.example.ktor.plugins.configureRouting
import io.ktor.features.NotFoundException
import io.ktor.http.HttpMethod
import io.ktor.http.HttpStatusCode
import io.ktor.server.testing.handleRequest
import io.ktor.server.testing.withTestApplication
import io.mockk.every
import io.mockk.just
import io.mockk.mockkObject
import io.mockk.runs
import io.mockk.unmockkObject
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest {

    @Test
    fun `when handling POST event, it calls perform on EventsStorage and returns http status code accepted`() {
        withTestApplication({ configureRouting() }) {
            mockkObject(EventsStorage)
            every { EventsStorage.perform(any()) } just runs

            handleRequest(HttpMethod.Post, "/event").apply {
                assertEquals(HttpStatusCode.Accepted, response.status())
            }

            unmockkObject(EventsStorage)
        }
    }

    @Test
    fun `when handling GET statistic, it returns the statistic and http status code accepted`() {
        val expectedStatistic = Statistic(10, 0.7796950936, 0.0473002568, 2127711810, 1194727708.0)
        withTestApplication({ configureRouting() }) {
            mockkObject(StatisticQuery)
            every { StatisticQuery.perform() } returns expectedStatistic

            handleRequest(HttpMethod.Get, "/stats").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertEquals(expectedStatistic.toString(), response.content)
            }

            unmockkObject(StatisticQuery)
        }
    }

    @Test
    fun `when handling GET statistic and StatisticQuery throws NotFoundException, it returns http status code not found`() {
        val exceptionMessage = "No statistic for last period!"
        withTestApplication({ configureRouting() }) {
            mockkObject(StatisticQuery)
            every { StatisticQuery.perform() } throws (NotFoundException(exceptionMessage))

            handleRequest(HttpMethod.Get, "/stats").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
                assertEquals(exceptionMessage, response.content)
            }

            unmockkObject(StatisticQuery)
        }
    }
}
