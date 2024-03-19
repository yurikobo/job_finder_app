package com.example.kotlincourse.data.repository

import com.example.kotlincourse.dao.DatabaseSingleton.dbQuery
import com.example.kotlincourse.data.models.Resume
import com.example.kotlincourse.data.models.Resumes
import com.example.kotlincourse.domain.repository.CandidateInfoRepository
import com.example.kotlincourse.domain.repository.EducationRepository
import com.example.kotlincourse.domain.repository.JobExperienceRepository
import com.example.kotlincourse.domain.repository.ResumeRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import javax.inject.Inject

class ResumeRepositoryExposedImpl @Inject constructor(
    private val candidateInfoRepository: CandidateInfoRepository,
    private val educationRepository: EducationRepository,
    private val jobExperienceRepository: JobExperienceRepository
) : ResumeRepository {

    private fun resultRowToResume(row: ResultRow) = Resume(
        id = row[Resumes.id],
        candidateInfo = null,
        educationList = null,
        jobExperienceList = null,
        freeForm = row[Resumes.freeForm]
    )


    override suspend fun findById(id: Long): Resume? = dbQuery {
        Resumes
            .select(Resumes.id eq id)
            .map(::resultRowToResume)
            .singleOrNull()
    }?.let {
        it.copy(
            candidateInfo = candidateInfoRepository.findByResumeId(it.id),
            educationList = educationRepository.findAllByResumeId(it.id) ?: emptyList(),
            jobExperienceList = jobExperienceRepository.findAllByResumeId(it.id) ?: emptyList()
        )
    } ?: dbQuery {
        Resumes.insert { it[freeForm] = "" }
            .resultedValues?.singleOrNull()?.let(::resultRowToResume)
    }


    override suspend fun save(entity: Resume) {
        dbQuery {
            Resumes.insert { it[freeForm] = entity.freeForm ?: "" }
        }
        entity.candidateInfo?.let { candidateInfoRepository.save(it) }
        entity.educationList?.let { educations -> educations.forEach { educationRepository.save(it) } }
        entity.jobExperienceList?.let { jobExperiences -> jobExperiences.forEach { jobExperienceRepository.save(it) } }
    }

    override suspend fun update(entity: Resume) {
        println(entity)
    }

    override suspend fun delete(entity: Resume) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Resume> {
        val resumesList = mutableListOf<Resume>()
        dbQuery {
            Resumes
                .selectAll()
                .map(::resultRowToResume)
        }.forEach { resume ->
            resumesList.add(
                resume.copy(
                    candidateInfo = candidateInfoRepository.findByResumeId(resume.id),
                    educationList = educationRepository.findAllByResumeId(resume.id),
                    jobExperienceList = jobExperienceRepository.findAllByResumeId(resume.id)
                )
            )
        }
        return resumesList
    }
}