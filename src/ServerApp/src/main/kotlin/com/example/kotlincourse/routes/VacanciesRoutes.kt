package com.example.kotlincourse.routes

import com.example.kotlincourse.data.repository.CompanyRepositoryImpl
import com.example.kotlincourse.data.repository.VacancyRepositoryImpl
import com.example.kotlincourse.domain.usecase.GetVacancyDetailsUseCase
import com.example.kotlincourse.domain.usecase.GetVacancyInfoListUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val companyRepository = CompanyRepositoryImpl()
private val vacancyRepository = VacancyRepositoryImpl()

fun Route.vacanciesRoute() {
    val getVacanciesListUseCase = GetVacancyInfoListUseCase(companyRepository)
    get("/vacancies") {
        call.respond(getVacanciesListUseCase.execute())
    }

}

fun Route.vacancyByIdRoute() {
    val getVacancyDetailsUseCase = GetVacancyDetailsUseCase(vacancyRepository)
    get("/vacancies/{id}") {
        val receivedId = call.parameters["id"]?.toLongOrNull()
        val response = if (receivedId != null) getVacancyDetailsUseCase.execute(receivedId) else null
        call.respond(response ?: "")
    }
}