package com.example.kotlincourse

import com.example.kotlincourse.data.CompanyData
import com.example.kotlincourse.data.models.VacancyInfo
import com.example.kotlincourse.plugins.configureSerialization
import com.example.kotlincourse.plugins.vacancyModule
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class VacancyRoutesTest {
    @Test
    fun testCompanyRouteFindAll() = testApplication {
        application {
            vacancyModule()
            configureSerialization()
        }
        client.get("/vacancies").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                CompanyData.companyList.flatMap { company ->
                    company.listOfVacancies.map { vacancy ->
                        VacancyInfo(
                            jobTitle = vacancy.profession,
                            candidateLevel = vacancy.level,
                            salaryLevel = vacancy.salary,
                            companyName = company.name
                        )
                    }
                },
                Json.decodeFromString(bodyAsText())
            )
        }

    }

    @Test
    fun testCompanyRouteFindById() = testApplication {
        application {
            vacancyModule()
            configureSerialization()
        }

        client.get("/vacancies/1").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                CompanyData.companyList.flatMap { it.listOfVacancies }[0],
                Json.decodeFromString(bodyAsText())
            )
        }

    }

    @Test
    fun testCompanyRouteIncorrectInput() = testApplication {
        application {
            vacancyModule()
            configureSerialization()
        }

        client.get("/vacancies/-1").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                "",
                bodyAsText()
            )
        }

        client.get("/vacancies/a").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                "",
                bodyAsText()
            )
        }
    }

}
