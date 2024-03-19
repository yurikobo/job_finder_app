package com.example.kotlincourse.data.repository

import com.example.kotlincourse.dao.DatabaseSingleton.dbQuery
import com.example.kotlincourse.data.models.Contact
import com.example.kotlincourse.data.models.Contacts
import com.example.kotlincourse.domain.repository.ContactRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import javax.inject.Inject

class ContactRepositoryExposedImpl @Inject constructor() : ContactRepository {

    private fun resultRowToContact(row: ResultRow) = Contact(
        phone = row[Contacts.phone],
        email = row[Contacts.email]
    )

    override suspend fun findByCandidateInfoId(id: Long): Contact? = dbQuery {
        Contacts.select { Contacts.candidateId eq id }
            .map(::resultRowToContact)
            .singleOrNull()
    }

    override suspend fun findById(id: Long): Contact? {
        TODO("Not yet implemented")
    }

    override suspend fun save(entity: Contact) {
        TODO("Not yet implemented")
    }

    override suspend fun update(entity: Contact) {
        TODO("Not yet implemented")
    }

    override suspend fun delete(entity: Contact) {
        TODO("Not yet implemented")
    }

    override suspend fun findAll(): List<Contact> {
        TODO("Not yet implemented")
    }
}