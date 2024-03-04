package com.example.kotlincourse.data.repository

import com.example.kotlincourse.data.CompanyData
import com.example.kotlincourse.data.models.Vacancy
import com.example.kotlincourse.domain.repository.VacancyRepository

class VacancyRepositoryImpl : VacancyRepository {

    override fun findById(id: Long): Vacancy? = findAll().find { it.id == id }

    override fun save(entity: Vacancy) {
        TODO("Not yet implemented")
    }

    override fun update(entity: Vacancy) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Vacancy) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Vacancy> {
        return CompanyData.companyList.flatMap { it.listOfVacancies }
    }
}