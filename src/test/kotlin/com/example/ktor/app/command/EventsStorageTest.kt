package com.example.ktor.app.command

import com.example.ktor.app.Event
import com.example.ktor.app.StoredEvents
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkObject
import io.mockk.unmockkObject
import io.mockk.verify
import org.junit.Test

class EventsStorageTest {

    @Test
    fun `when performing events storage, it calls Events to do mapping and StoredEvents to add events`() {
        val emptyEventList = emptyList<Event>()
        val eventsString = "1607341341814,0.0442672968,1282509067\n" +
                "1607341339814,0.0473002568,1785397644"
        mockkObject(Event)
        mockkObject(StoredEvents)
        every { StoredEvents.add(emptyEventList) } returns mockk()
        every { Event.mapToEvents(eventsString) } returns emptyEventList

        EventsStorage.perform(eventsString)

        verify { Event.mapToEvents(eventsString) }
        verify { StoredEvents.add(emptyEventList) }

        unmockkObject(Event)
    }
}
