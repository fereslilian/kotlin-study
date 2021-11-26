package com.example.ktor.plugins

import com.example.ktor.app.command.EventsStorage
import com.example.ktor.app.query.StatisticQuery
import io.ktor.application.Application
import io.ktor.application.call
import io.ktor.features.NotFoundException
import io.ktor.http.HttpStatusCode
import io.ktor.request.receiveText
import io.ktor.response.respond
import io.ktor.routing.get
import io.ktor.routing.post
import io.ktor.routing.routing

fun Application.configureRouting() {

    routing {
        post("/event") {
            EventsStorage.perform(call.receiveText())
            call.respond(HttpStatusCode.Accepted)
        }

        get("/stats") {
            try {
                val statistic = StatisticQuery.perform()
                call.respond(HttpStatusCode.OK, statistic.toString())
            } catch (ex: NotFoundException) {
                call.respond(HttpStatusCode.NotFound, ex.message!!)
            }
        }
    }
}
