package com.example.kotlincourse.plugins

import com.example.kotlincourse.routes.vacanciesRoute
import com.example.kotlincourse.routes.vacancyByIdRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.vacancyModule() {
    routing {
        vacanciesRoute()
        vacancyByIdRoute()
    }
}