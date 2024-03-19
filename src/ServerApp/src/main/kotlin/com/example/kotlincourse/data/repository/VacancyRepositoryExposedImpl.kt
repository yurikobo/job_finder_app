package com.example.kotlincourse.data.repository

import com.example.kotlincourse.dao.DatabaseSingleton.dbQuery
import com.example.kotlincourse.data.models.*
import com.example.kotlincourse.domain.repository.VacancyRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import javax.inject.Inject

class VacancyRepositoryExposedImpl @Inject constructor() : VacancyRepository {


    private fun resultRowToVacancy(row: ResultRow) = Vacancy(
        id = row[Vacancies.id],
        profession = Profession.valueOf(row[Professions.name]),
        level = CandidateLevel.valueOf(row[CandidateLevels.name]),
        salary = row[Vacancies.salary],
        description = row[Vacancies.description]
    )

    override suspend fun findById(id: Long): Vacancy? = dbQuery {
        Vacancies
            .leftJoin(Professions, { Vacancies.professionId }, { Professions.id })
            .leftJoin(CandidateLevels, { Vacancies.levelId }, { CandidateLevels.id })
            .select(Vacancies.id eq id)
            .map(::resultRowToVacancy)
            .singleOrNull()
    }

    override suspend fun save(entity: Vacancy) {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: Vacancy) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: Vacancy) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Vacancy> = dbQuery {
        Vacancies
            .leftJoin(Professions, { Vacancies.professionId }, { Professions.id })
            .leftJoin(CandidateLevels, { Vacancies.levelId }, { CandidateLevels.id })
            .selectAll()
            .map(::resultRowToVacancy)
    }

    override suspend fun findAllByCompanyId(id: Long): List<Vacancy> = dbQuery {
        Vacancies
            .leftJoin(Professions, { Vacancies.professionId }, { Professions.id })
            .leftJoin(CandidateLevels, { Vacancies.levelId }, { CandidateLevels.id })
            .select { Vacancies.companyId eq id }
            .map(::resultRowToVacancy)
    }
}