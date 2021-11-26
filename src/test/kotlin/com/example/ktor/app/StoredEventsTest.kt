package com.example.ktor.app

import org.junit.Test
import kotlin.test.assertEquals

class StoredEventsTest {

    @Test
    fun `when adding events, it increase storedEvents size`() {
        val events = listOf(
            Event(1607341341814, 0.0442672968, 1282509067),
            Event(1607341339814, 0.0473002568, 1785397644),
        )

        StoredEvents.add(events)

        assertEquals(events.size, StoredEvents.getStoredEvents().size)
    }

    @Test
    fun `when getting events within past sixty seconds, it returned the events of this range`() {
        val millisecondsToDecrease = 61000
        val events = listOf(
            Event(System.currentTimeMillis(), 0.0442672968, 1282509067),
            Event(System.currentTimeMillis() - millisecondsToDecrease, 0.0473002568, 1785397644),
        )

        StoredEvents.add(events)

        assertEquals(1, StoredEvents.getWithinPastSixtySeconds().size)
    }
}
