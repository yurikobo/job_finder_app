package com.example.kotlincourse.data.repository

import com.example.kotlincourse.dao.DatabaseSingleton.dbQuery
import com.example.kotlincourse.data.models.Companies
import com.example.kotlincourse.data.models.Company
import com.example.kotlincourse.data.models.FieldOfActivity
import com.example.kotlincourse.data.models.FieldsOfActivity
import com.example.kotlincourse.domain.repository.CompanyRepository
import com.example.kotlincourse.domain.repository.VacancyRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.leftJoin
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import javax.inject.Inject

class CompanyRepositoryExposedImpl @Inject constructor(private val vacancyRepository: VacancyRepository) :
    CompanyRepository {

    private fun resultRowToCompany(row: ResultRow) = Company(
        id = row[Companies.id],
        name = row[Companies.name],
        fieldOfActivity = FieldOfActivity.valueOf(row[FieldsOfActivity.name]),
        listOfVacancies = emptyList(),
        contact = row[Companies.contact]
    )


    override suspend fun findById(id: Long): Company? = dbQuery {
        Companies
            .leftJoin(FieldsOfActivity, { Companies.fieldOfActivity }, { FieldsOfActivity.id })
            .select { Companies.id eq id }
            .map(::resultRowToCompany)
            .singleOrNull()
            ?.let { it.copy(listOfVacancies = vacancyRepository.findAllByCompanyId(it.id) ?: emptyList()) }
    }

    override suspend fun save(entity: Company) {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: Company) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: Company) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Company> {
        val companiesList = mutableListOf<Company>()
        dbQuery {
            Companies
                .leftJoin(FieldsOfActivity, { fieldOfActivity }, { id })
                .selectAll().map(::resultRowToCompany)
        }.forEach { company ->
            companiesList.add(
                company.copy(listOfVacancies = vacancyRepository.findAllByCompanyId(company.id) ?: emptyList())
            )
        }
        return companiesList
    }


}