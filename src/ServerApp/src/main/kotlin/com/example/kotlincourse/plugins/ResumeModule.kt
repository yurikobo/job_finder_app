package com.example.kotlincourse.plugins

import com.example.kotlincourse.domain.usecase.GetResumeListUseCase
import com.example.kotlincourse.domain.usecase.GetResumeUseCase
import com.example.kotlincourse.domain.usecase.UpdateResumeUseCase
import com.example.kotlincourse.routes.resumeByIdRoute
import com.example.kotlincourse.routes.resumeTagsRoute
import com.example.kotlincourse.routes.resumesRoute
import com.example.kotlincourse.routes.updateResumeByIdRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.resumeModule(
    resumeListUseCase: GetResumeListUseCase,
    resumeUseCase: GetResumeUseCase,
    updateResumeUseCase: UpdateResumeUseCase
) {
    routing {
        resumesRoute(resumeListUseCase)
        resumeByIdRoute(resumeUseCase)
        resumeTagsRoute(resumeUseCase)
        updateResumeByIdRoute(updateResumeUseCase)
    }
}