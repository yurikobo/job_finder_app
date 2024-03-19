package com.example.kotlincourse.plugins

import com.example.kotlincourse.domain.usecase.GetCompaniesInfoListUseCase
import com.example.kotlincourse.domain.usecase.GetCompanyDetailsUseCase
import com.example.kotlincourse.routes.companiesRoute
import com.example.kotlincourse.routes.companyByIdRoute
import io.ktor.server.application.*
import io.ktor.server.routing.*

fun Application.companyModule(
    companiesInfoListUseCase: GetCompaniesInfoListUseCase,
    companyDetailsUseCase: GetCompanyDetailsUseCase
) {
    routing {
        companiesRoute(companiesInfoListUseCase)
        companyByIdRoute(companyDetailsUseCase)
    }
}