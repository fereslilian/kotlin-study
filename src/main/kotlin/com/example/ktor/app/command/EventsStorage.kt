package com.example.ktor.app.command

import com.example.ktor.app.Event
import com.example.ktor.app.StoredEvents

object EventsStorage {
    fun perform(events: String) = StoredEvents.add(Event.mapToEvents(events))
}
