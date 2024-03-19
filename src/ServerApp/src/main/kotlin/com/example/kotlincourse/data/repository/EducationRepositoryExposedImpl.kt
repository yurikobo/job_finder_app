package com.example.kotlincourse.data.repository

import com.example.kotlincourse.dao.DatabaseSingleton.dbQuery
import com.example.kotlincourse.data.models.Education
import com.example.kotlincourse.data.models.EducationType
import com.example.kotlincourse.data.models.EducationTypes
import com.example.kotlincourse.data.models.Educations
import com.example.kotlincourse.domain.repository.EducationRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.time.Year
import javax.inject.Inject

class EducationRepositoryExposedImpl @Inject constructor() : EducationRepository {

    private fun resultRowToEducation(row: ResultRow) = Education(
        type = EducationType.valueOf(row[EducationTypes.educationType]),
        startYear = Year.of(row[Educations.startYear].year),
        endYear = Year.of(row[Educations.endYear].year),
        description = row[Educations.description]
    )

    override suspend fun findAllByResumeId(id: Long): List<Education> = dbQuery {
        Educations
            .leftJoin(EducationTypes, { Educations.educationTypeId }, { EducationTypes.id })
            .select { Educations.resumeId eq id }
            .map(::resultRowToEducation)
    }

    override suspend fun findById(id: Long): Education? = dbQuery {
        Educations
            .leftJoin(EducationTypes, { Educations.educationTypeId }, { EducationTypes.id })
            .select { Educations.id eq id }
            .map(::resultRowToEducation)
            .singleOrNull()
    }

    override suspend fun save(entity: Education) {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: Education) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: Education) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Education> = dbQuery {
        Educations
            .leftJoin(EducationTypes, { Educations.educationTypeId }, { EducationTypes.id })
            .selectAll()
            .map(::resultRowToEducation)
    }
}