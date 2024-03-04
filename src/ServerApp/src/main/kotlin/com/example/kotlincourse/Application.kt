package com.example.kotlincourse

import com.example.kotlincourse.plugins.companyModule
import com.example.kotlincourse.plugins.configureSerialization
import com.example.kotlincourse.plugins.resumeModule
import com.example.kotlincourse.plugins.vacancyModule
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    companyModule()
    vacancyModule()
    resumeModule()
    configureSerialization()
}

