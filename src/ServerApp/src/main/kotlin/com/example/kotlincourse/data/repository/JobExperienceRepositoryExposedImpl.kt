package com.example.kotlincourse.data.repository

import com.example.kotlincourse.dao.DatabaseSingleton.dbQuery
import com.example.kotlincourse.data.models.JobExperience
import com.example.kotlincourse.data.models.JobExperiences
import com.example.kotlincourse.domain.repository.JobExperienceRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.time.YearMonth
import javax.inject.Inject

class JobExperienceRepositoryExposedImpl @Inject constructor() : JobExperienceRepository {

    private fun resultRowToJobExperience(row: ResultRow) = JobExperience(
        dateStart = YearMonth.of(row[JobExperiences.dateStart].year, row[JobExperiences.dateStart].monthNumber),
        dateEnd = YearMonth.of(row[JobExperiences.dateEnd].year, row[JobExperiences.dateEnd].monthNumber),
        companyName = row[JobExperiences.companyName],
        description = row[JobExperiences.description]
    )

    override suspend fun findAllByResumeId(id: Long): List<JobExperience> = dbQuery {
        JobExperiences
            .select(JobExperiences.resumeId eq id)
            .map(::resultRowToJobExperience)
    }

    override suspend fun findById(id: Long): JobExperience? = dbQuery {
        JobExperiences
            .select(JobExperiences.id eq id)
            .map(::resultRowToJobExperience)
            .singleOrNull()
    }

    override suspend fun save(entity: JobExperience) {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: JobExperience) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: JobExperience) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<JobExperience> = dbQuery {
        JobExperiences
            .selectAll()
            .map(::resultRowToJobExperience)
    }
}