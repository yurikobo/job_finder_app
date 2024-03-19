package com.example.kotlincourse

import com.example.kotlincourse.dao.DatabaseSingleton
import com.example.kotlincourse.di.AppContainer
import com.example.kotlincourse.di.DaggerAppContainer
import com.example.kotlincourse.plugins.companyModule
import com.example.kotlincourse.plugins.configureSerialization
import com.example.kotlincourse.plugins.resumeModule
import com.example.kotlincourse.plugins.vacancyModule
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*

lateinit var appContainer: AppContainer

fun main() {
    embeddedServer(CIO, port = 8080, host = "0.0.0.0", module = Application::module)
        .start(wait = true)
}

fun Application.module() {
    val appContainer = DaggerAppContainer.create()
    DatabaseSingleton.init()
    configureSerialization()
    companyModule(appContainer.getCompaniesInfoListUseCase(), appContainer.getCompanyDetailsUseCase())
    vacancyModule(appContainer.getVacanciesInfoListUseCase(), appContainer.getVacancyDetailsUseCase())
    resumeModule(
        appContainer.getResumeListUseCase(),
        appContainer.getResumeUseCase(),
        appContainer.getUpdateResumeUseCase()
    )
}

