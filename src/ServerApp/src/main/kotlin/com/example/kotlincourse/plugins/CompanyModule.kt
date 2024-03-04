package com.example.kotlincourse.plugins

import com.example.kotlincourse.routes.companiesRoute
import com.example.kotlincourse.routes.companyByIdRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.companyModule() {
    routing {
        companiesRoute()
        companyByIdRoute()
    }
}