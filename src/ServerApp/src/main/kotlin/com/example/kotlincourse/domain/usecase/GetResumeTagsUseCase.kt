package com.example.kotlincourse.domain.usecase

import com.example.kotlincourse.data.models.CandidateLevel
import com.example.kotlincourse.data.models.JobExperience
import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.data.models.ResumeTag
import io.ktor.util.logging.*
import java.time.Period

internal val LOGGER = KtorSimpleLogger("com.example.kotlincourse")

class GetResumeTagsUseCase {


    suspend fun execute(resume: Resume): List<ResumeTag> {
        val tagList = mutableListOf<ResumeTag>()
        if (resume.candidateInfo != null) {
            val professionTag = resume.candidateInfo.profession
            LOGGER.info(professionTag.name)
            tagList.add(professionTag)
            if (!resume.jobExperienceList.isNullOrEmpty()) {
                getCandidateLevel(resume.jobExperienceList)?.let {
                    LOGGER.info(it.name)
                    tagList.add(it)
                }
            }
        }
        return tagList
    }

    private fun getCandidateLevel(jobExperienceList: List<JobExperience>): CandidateLevel? {
        val experienceAsInt = jobExperienceList.sumOf {
            val date = it.dateEnd.minus(Period.of(it.dateStart.year, it.dateStart.monthValue, 0))
            date.year * 12 + date.monthValue
        }
        return CandidateLevel.values().firstOrNull { experienceAsInt / 12 in it.seniorityRange }
    }
}