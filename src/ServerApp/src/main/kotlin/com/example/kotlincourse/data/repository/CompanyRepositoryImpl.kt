package com.example.kotlincourse.data.repository

import com.example.kotlincourse.data.CompanyData
import com.example.kotlincourse.data.models.Company
import com.example.kotlincourse.domain.repository.CompanyRepository

class CompanyRepositoryImpl : CompanyRepository {

    override fun findById(id: Long): Company? = CompanyData.companyList.find { it.id == id }

    override fun save(entity: Company) {
        TODO("Not yet implemented")
    }

    override fun update(entity: Company) {
        TODO("Not yet implemented")
    }

    override fun delete(entity: Company) {
        TODO("Not yet implemented")
    }

    override fun findAll(): List<Company> {
        return CompanyData.companyList
    }
}