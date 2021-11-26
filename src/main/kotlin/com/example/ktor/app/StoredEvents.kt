package com.example.ktor.app

import java.util.Collections

object StoredEvents {

    private var storedEvents = mutableListOf<Event>()

    fun add(events: List<Event>) {
        synchronized(storedEvents) {
            storedEvents.addAll(events)
        }
    }

    fun getWithinPastSixtySeconds(): List<Event> {
        synchronized(storedEvents) {
            val clearedEvents = clearExpired()
            return Collections.unmodifiableList(clearedEvents)
        }
    }

    fun getStoredEvents(): MutableList<Event> = Collections.unmodifiableList(storedEvents)

    private fun clearExpired(): List<Event> {
        val millisecondsToExpire = 60000
        val minAcceptableTimestamp = System.currentTimeMillis() - millisecondsToExpire
        val clearedEvents = mutableListOf<Event>().apply { addAll(storedEvents) }
        clearedEvents.removeIf { event -> event.timestamp <= minAcceptableTimestamp }
        return clearedEvents
    }
}
