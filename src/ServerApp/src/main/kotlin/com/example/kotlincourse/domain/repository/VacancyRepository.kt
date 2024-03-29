package com.example.kotlincourse.domain.repository

import com.example.kotlincourse.data.models.Vacancy

interface VacancyRepository : CrudRepository<Vacancy> {
    suspend fun findAllByCompanyId(id: Long): List<Vacancy>?
}