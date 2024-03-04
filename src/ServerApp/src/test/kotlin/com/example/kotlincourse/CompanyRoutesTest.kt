package com.example.kotlincourse

import com.example.kotlincourse.data.CompanyData
import com.example.kotlincourse.data.models.CompanyInfo
import com.example.kotlincourse.plugins.companyModule
import com.example.kotlincourse.plugins.configureSerialization
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals

class CompanyRoutesTest {
    @Test
    fun testCompanyRouteFindAll() = testApplication {
        application {
            companyModule()
            configureSerialization()
        }
        client.get("/companies").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                CompanyData.companyList.map { CompanyInfo(it.name, it.fieldOfActivity) },
                Json.decodeFromString(bodyAsText())
            )
        }

    }

    @Test
    fun testCompanyRouteFindById() = testApplication {
        application {
            companyModule()
            configureSerialization()
        }

        client.get("/companies/1").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                CompanyData.companyList[0],
                Json.decodeFromString(bodyAsText())
            )
        }
    }

    @Test
    fun testCompanyRouteIncorrectInput() = testApplication {
        application {
            companyModule()
            configureSerialization()
        }

        client.get("/companies/-1").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                "",
                bodyAsText()
            )
        }

        client.get("/companies/a").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                "",
                bodyAsText()
            )
        }
    }

}
