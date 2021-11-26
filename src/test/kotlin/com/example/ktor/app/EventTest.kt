package com.example.ktor.app

import com.example.ktor.app.Event.Companion
import org.junit.Assert
import org.junit.Test

class EventTest {

    @Test
    fun `when mapping to events a string with all content lines acceptable, it creates the event list`() {
        val eventsString = "1607341341814,0.0442672968,1282509067\n" +
                "1607341339814,0.0473002568,1785397644"
        val expectedEvents = listOf(
            Event(1607341341814, 0.0442672968, 1282509067),
            Event(1607341339814, 0.0473002568, 1785397644),
        )

        val events = Event.mapToEvents(eventsString)

        Assert.assertEquals(expectedEvents, events)
    }

    @Test
    fun `when mapping to events a string with invalid line content, it creates an empty event list`() {
        val eventsString = "1607341341814,0.0442672968,1282509067\n" +
                "1607341339814,0.0473002568,1785397644"
        val expectedEvents = listOf(
            Event(1607341341814, 0.0442672968, 1282509067),
            Event(1607341339814, 0.0473002568, 1785397644),
        )

        val events = Event.mapToEvents(eventsString)

        Assert.assertEquals(expectedEvents, events)
    }

}
