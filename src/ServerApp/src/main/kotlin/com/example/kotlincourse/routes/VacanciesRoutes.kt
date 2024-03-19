package com.example.kotlincourse.routes

import com.example.kotlincourse.domain.usecase.GetVacancyDetailsUseCase
import com.example.kotlincourse.domain.usecase.GetVacancyInfoListUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.vacanciesRoute(getVacanciesInfoListUseCase: GetVacancyInfoListUseCase) {
    get("/vacancies") {
        call.respond(getVacanciesInfoListUseCase.execute())
    }

}

fun Route.vacancyByIdRoute(getVacancyDetailsUseCase: GetVacancyDetailsUseCase) {
    get("/vacancies/{id}") {
        val receivedId = call.parameters["id"]?.toLongOrNull()
        val response = if (receivedId != null) getVacancyDetailsUseCase.execute(receivedId) else null
        call.respond(response ?: "")
    }
}