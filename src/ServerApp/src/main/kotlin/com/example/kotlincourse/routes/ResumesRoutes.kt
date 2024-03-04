package com.example.kotlincourse.routes

import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.data.models.ResumeWithTags
import com.example.kotlincourse.data.repository.ResumeRepositoryImpl
import com.example.kotlincourse.domain.usecase.GetResumeListUseCase
import com.example.kotlincourse.domain.usecase.GetResumeTagsUseCase
import com.example.kotlincourse.domain.usecase.GetResumeUseCase
import com.example.kotlincourse.domain.usecase.UpdateResumeUseCase
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val resumeRepository = ResumeRepositoryImpl()
private val json = Json {
    prettyPrint = true
    useArrayPolymorphism = true
}

fun Route.resumesRoute() {
    val getResumeListUseCase = GetResumeListUseCase(resumeRepository)
    get("/resumes") {
        call.respond(getResumeListUseCase.execute())
    }

}

fun Route.resumeByIdRoute() {
    val getResumeUseCase = GetResumeUseCase(resumeRepository)
    get("/resumes/{id}") {
        val receivedId = call.parameters["id"]?.toLongOrNull()
        val response = if (receivedId != null) getResumeUseCase.execute(receivedId) else null
        val tagList = if (response != null) GetResumeTagsUseCase().execute(response) else null
        call.respond(if (response != null && tagList != null) ResumeWithTags(response, tagList) else "")

    }
}

fun Route.resumeTagsRoute() {
    val getResumeUseCase = GetResumeUseCase(resumeRepository)
    get("/resumes/{id}/tags") {
        val receivedId = call.parameters["id"]?.toLongOrNull()
        val response = if (receivedId != null) getResumeUseCase.execute(receivedId) else null
        call.respond(
            if (response != null) {
                json.encodeToString(GetResumeTagsUseCase().execute(response))
            } else {
                ""
            }
        )
    }
}

fun Route.updateResumeByIdRoute() {
    val updateResumeUseCase = UpdateResumeUseCase(resumeRepository)
    post("/resumes/{id}") {
        val receivedId = call.parameters["id"]?.toLongOrNull()
        if (receivedId != null) {
            val resume = call.receive<Resume>()
            val tagList = GetResumeTagsUseCase().execute(resume) //  it'll be saved later
            val useCaseResponse = updateResumeUseCase.execute(
                Resume(
                    id = receivedId,
                    candidateInfo = resume.candidateInfo,
                    educationList = resume.educationList,
                    jobExperienceList = resume.jobExperienceList,
                    freeForm = resume.freeForm
                )
            )
            call.respond(useCaseResponse)

        } else {
            call.respond("")
        }
    }
}



