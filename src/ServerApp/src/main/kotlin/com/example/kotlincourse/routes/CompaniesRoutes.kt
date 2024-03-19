package com.example.kotlincourse.routes

import com.example.kotlincourse.domain.usecase.GetCompaniesInfoListUseCase
import com.example.kotlincourse.domain.usecase.GetCompanyDetailsUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.companiesRoute(getCompaniesInfoListUseCase: GetCompaniesInfoListUseCase) {
    get("/companies") {
        call.respond(getCompaniesInfoListUseCase.execute())
    }
}

fun Route.companyByIdRoute(getCompanyDetailsUseCase: GetCompanyDetailsUseCase) {
    get("/companies/{id}") {
        val receivedId = call.parameters["id"]?.toLongOrNull()
        val response = if (receivedId != null) getCompanyDetailsUseCase.execute(receivedId) else null
        call.respond(response ?: "")
    }
}
