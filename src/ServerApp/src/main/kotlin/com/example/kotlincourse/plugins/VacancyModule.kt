package com.example.kotlincourse.plugins

import com.example.kotlincourse.domain.usecase.GetVacancyDetailsUseCase
import com.example.kotlincourse.domain.usecase.GetVacancyInfoListUseCase
import com.example.kotlincourse.routes.vacanciesRoute
import com.example.kotlincourse.routes.vacancyByIdRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.vacancyModule(
    vacanciesInfoListUseCase: GetVacancyInfoListUseCase,
    vacancyDetailsUseCase: GetVacancyDetailsUseCase
) {
    routing {
        vacanciesRoute(vacanciesInfoListUseCase)
        vacancyByIdRoute(vacancyDetailsUseCase)
    }
}