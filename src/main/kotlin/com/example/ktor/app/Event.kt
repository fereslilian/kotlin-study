package com.example.ktor.app

data class Event(
    val timestamp: Long,
    val x: Double,
    val y: Int
) {
    companion object {

        fun mapToEvents(events: String): List<Event> {
            return try {
                val eventList: List<Event> = events.lines()
                    .map { eventItem -> eventItem.split(",") }
                    .map { eventData ->
                        Event(
                            timestamp = eventData[0].trim().toLong(),
                            x = eventData[1].trim().toDouble(),
                            y = eventData[2].trim().toInt()
                        )
                    }
                eventList
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }
}
