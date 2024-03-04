package com.example.kotlincourse.plugins

import com.example.kotlincourse.routes.resumeByIdRoute
import com.example.kotlincourse.routes.resumeTagsRoute
import com.example.kotlincourse.routes.resumesRoute
import com.example.kotlincourse.routes.updateResumeByIdRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.resumeModule() {
    routing {
        resumesRoute()
        resumeByIdRoute()
        resumeTagsRoute()
        updateResumeByIdRoute()
    }
}