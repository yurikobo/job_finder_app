package com.example.kotlincourse.routes

import com.example.kotlincourse.data.repository.CompanyRepositoryImpl
import com.example.kotlincourse.domain.usecase.GetCompaniesInfoListUseCase
import com.example.kotlincourse.domain.usecase.GetCompanyDetailsUseCase
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

private val companyRepository = CompanyRepositoryImpl()

fun Route.companiesRoute() {
    val getCompaniesInfoListUseCase = GetCompaniesInfoListUseCase(companyRepository)
    get("/companies") {
        call.respond(getCompaniesInfoListUseCase.execute())
    }
}

fun Route.companyByIdRoute() {
    val getCompanyDetailsUseCase = GetCompanyDetailsUseCase(companyRepository)
    get("/companies/{id}") {
        val receivedId = call.parameters["id"]?.toLongOrNull()
        val response = if (receivedId != null) getCompanyDetailsUseCase.execute(receivedId) else null
        call.respond(response ?: "")
    }
}