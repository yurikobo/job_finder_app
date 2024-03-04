package com.example.kotlincourse

import com.example.kotlincourse.data.ResumeData
import com.example.kotlincourse.data.models.*
import com.example.kotlincourse.plugins.configureSerialization
import com.example.kotlincourse.plugins.resumeModule
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.testing.*
import kotlinx.serialization.json.Json
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals

class ResumeRoutesTest {
    @Test
    fun testResumeRouteFindAll() = testApplication {
        application {
            resumeModule()
            configureSerialization()
        }
        client.get("/resumes").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                ResumeData.resumeList,
                Json.decodeFromString(bodyAsText())
            )
        }

    }

    @Test
    fun testResumeRouteFindById() = testApplication {
        application {
            resumeModule()
            configureSerialization()
        }

        client.get("/resumes/1").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                ResumeData.resumeList[0],
                Json.decodeFromString(bodyAsText())
            )
        }

    }

    @Test
    fun testResumeRouteIncorrectInput() = testApplication {
        application {
            resumeModule()
            configureSerialization()
        }

        client.get("/resumes/-1").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                "",
                bodyAsText()
            )
        }

        client.get("/resumes/a").apply {
            assertEquals(HttpStatusCode.OK, status)

            assertEquals(
                "",
                bodyAsText()
            )
        }
    }

    @Test
    fun testResumeRoutesPostValidIdTest() = testApplication {
        application {
            resumeModule()
            configureSerialization()
        }
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }
        val response = client.post("/resumes/1") {
            contentType(ContentType.Application.Json)
            setBody(

                Resume(
                    id = 500,
                    candidateInfo = CandidateInfo(
                        name = "Test name",
                        profession = Profession.PROJECT_MANAGER,
                        gender = Gender.FEMALE,
                        birthDate = LocalDate.of(1, 1, 1),
                        contact = Contact(
                            phone = "8800",
                            email = "email.com"
                        ),
                        relocation = Relocation.PREFERABLE
                    ),
                    educationList = emptyList(),
                    jobExperienceList = emptyList(),
                    freeForm = "testing"
                )
            )
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            "Resume with id(1) was successfully updated",
            response.bodyAsText()
        )
    }


    @Test
    fun testResumeRoutesPostInvalidIdTest() = testApplication {
        application {
            resumeModule()
            configureSerialization()
        }
        val client = createClient {
            install(io.ktor.client.plugins.contentnegotiation.ContentNegotiation) {
                json()
            }
        }
        val response = client.post("/resumes/-1") {
            contentType(ContentType.Application.Json)
            setBody(

                Resume(
                    id = 500,
                    candidateInfo = CandidateInfo(
                        name = "Test name",
                        profession = Profession.PROJECT_MANAGER,
                        gender = Gender.FEMALE,
                        birthDate = LocalDate.of(1, 1, 1),
                        contact = Contact(
                            phone = "8800",
                            email = "email.com"
                        ),
                        relocation = Relocation.PREFERABLE
                    ),
                    educationList = emptyList(),
                    jobExperienceList = emptyList(),
                    freeForm = "testing"
                )
            )
        }
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals(
            "No resume with id(-1) was found",
            response.bodyAsText()
        )

        val anotherResponse = client.post("/resumes/a") {
            contentType(ContentType.Application.Json)
            setBody(

                Resume(
                    id = 500,
                    candidateInfo = CandidateInfo(
                        name = "Test name",
                        profession = Profession.PROJECT_MANAGER,
                        gender = Gender.FEMALE,
                        birthDate = LocalDate.of(1, 1, 1),
                        contact = Contact(
                            phone = "8800",
                            email = "email.com"
                        ),
                        relocation = Relocation.PREFERABLE
                    ),
                    educationList = emptyList(),
                    jobExperienceList = emptyList(),
                    freeForm = "testing"
                )
            )
        }
        assertEquals(HttpStatusCode.OK, anotherResponse.status)
        assertEquals(
            "",
            anotherResponse.bodyAsText()
        )
    }

    @Test
    fun testResumeRouteTags() = testApplication {
        application {
            resumeModule()
            configureSerialization()
        }

        val json = Json {
            prettyPrint = true
            useArrayPolymorphism = true
        }

        client.get("/resumes/1/tags").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(
                listOf<ResumeTag>(Profession.QA, CandidateLevel.JUNIOR),
                json.decodeFromString(bodyAsText())
            )
        }

        client.get("/resumes/2/tags").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals(
                listOf<ResumeTag>(Profession.DEVELOPER, CandidateLevel.JUNIOR),
                json.decodeFromString(bodyAsText())
            )
        }

    }


}
